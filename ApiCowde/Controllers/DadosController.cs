using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using MySql.Data.MySqlClient;
using Newtonsoft.Json;
using MD5Hash;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace ApiCowde.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class DadosController : ControllerBase
    {
        //login
        //editar
        //listar

        //static MySqlConnection con = new MySqlConnection("server=localhost;database=db_cowde;user id=root; password=1234");
        static MySqlConnection con = new MySqlConnection("server=ESN509VMYSQL;database=db_cowde;user id=aluno; password=Senai1234");

        [HttpGet]
        public IActionResult ListarDados()
        {
            MySqlCommand cmd = new MySqlCommand("SELECT * FROM tb_usuario;", con);
            con.Open();
            //lista de dados que vai ser mostrada
            List<UsuarioListar> lista = new List<UsuarioListar>();
            MySqlDataReader reader = cmd.ExecuteReader();
            while (reader.Read())
            {
                lista.Add(new UsuarioListar(reader.GetString("nome"), reader.GetString("nome_usuario"), reader.GetString("email")));
            }
            con.Close();
            return Ok(lista);
        }

        [HttpPost]
        [Route("Perfil")]
        public IActionResult ListarDados([FromBody] CadastroLogin cadastroLogin)
        {
            string nome = "", nome_usuario = "";
            try
            {
                con.Open();
                MySqlCommand cmd = new MySqlCommand("SELECT * FROM tb_usuario WHERE email = @email", con);
                cmd.Parameters.AddWithValue("@email", cadastroLogin.email);
                //lista de dados que vai ser mostrada
                MySqlDataReader reader = cmd.ExecuteReader();
                while (reader.Read())
                {
                    nome = reader.GetString("nome");
                    nome_usuario = reader.GetString("nome_usuario");

                }

                con.Close();

                return Ok(new { nome = nome, nome_usuario = nome_usuario });

            } catch (Exception exc) {

                //se o estado da conexão ainda estiver em "open"
                if (con.State == System.Data.ConnectionState.Open)
                {
                    con.Close();
                }
                return NotFound(exc);
            }
        }

        private IActionResult Ok(object p1, object p2)
        {
            throw new NotImplementedException();
        }

        [HttpPost]
        [Route("Login")]
        public IActionResult Login([FromBody] UsuarioCadastrado usuarioCasdastrado)
        {
            try
            {
                con.Open();

                MySqlCommand cmd = new MySqlCommand("SELECT * FROM tb_usuario WHERE email = @email AND senha = @senha", con);
                cmd.Parameters.AddWithValue("@email", usuarioCasdastrado.email);
                cmd.Parameters.AddWithValue("@senha", usuarioCasdastrado.senha);
                MySqlDataReader resultado = cmd.ExecuteReader();
                if (resultado.Read())
                {
                    con.Close();
                    return Ok(new { mensagem = "Aprovado" });
                }
                else
                {
                    con.Close();
                    return Ok(new { mensagem = "Negado" });
                }

            }
            catch (Exception exc)
            {
                //se o estado da conexão ainda estiver em "open"
                if (con.State == System.Data.ConnectionState.Open)
                {
                    con.Close();
                }
                return NotFound(exc);
            }
        }

        [HttpPost]
        [Route("Cadastrar")]
        public IActionResult Cadastrar([FromBody] CadastroLogin cadastroLogin)
        {
            //tentando fazer um procedimento
            try
            {
                con.Open();

                MySqlCommand cmd = new MySqlCommand(
                "INSERT INTO tb_usuario (nome,nome_usuario, email, idade, senha) VALUES(@nome,@nome_usuario, @email, @idade, @senha)", con);

                cmd.Parameters.AddWithValue("@nome", cadastroLogin.nome);
                cmd.Parameters.AddWithValue("@nome_usuario", cadastroLogin.nome_usuario);
                cmd.Parameters.AddWithValue("@email", cadastroLogin.email);
                cmd.Parameters.AddWithValue("@idade", cadastroLogin.idade);
                cmd.Parameters.AddWithValue("@senha", cadastroLogin.senha);

                cmd.ExecuteNonQuery();
                con.Close();

                return Ok(new { mensagem = "Aprovado" });
            }
            catch (Exception exc)
            {
                if (con.State == System.Data.ConnectionState.Open) {
                    con.Close();
                }

                return Ok(new { mensagem = "Reprovado" });
            }
        }

        [HttpPost]
        [Route("Atualizar")]
        public IActionResult Atualizar([FromBody] CadastroLogin cadastro)
        {
            CadastroLogin cadastroLeitura = null;

            try
            {
                con.Open();

                MySql.Data.MySqlClient.MySqlCommand cmd = new MySql.Data.MySqlClient.MySqlCommand("SELECT * FROM tb_usuario WHERE email = @email; ", con);
                cmd.Parameters.AddWithValue("@email", cadastro.email);

                MySql.Data.MySqlClient.MySqlDataReader reader = cmd.ExecuteReader();
                while (reader.Read())
                {
                    //Atribuir os dados do banco ao objeto
                    cadastroLeitura = new CadastroLogin(reader.GetInt32("id_usuario"), reader.GetString("nome"), reader.GetString("nome_usuario"),
                    reader.GetString("email"), reader.GetInt32("idade"), reader.GetString("senha"));
                }

                con.Close();

                con.Open();

                MySqlCommand qry = new MySqlCommand(
               //o parametro é o nome da coluna no bcd
               "UPDATE tb_usuario SET nome = @nome WHERE id_usuario = @id_usuario", con);//comando mysql para adicionar informações em uma tabela

                qry.Parameters.AddWithValue("@id_usuario", cadastroLeitura.id_usuario);

                if (cadastro.nome != null || cadastro.nome != "")
                {
                    qry.Parameters.AddWithValue("@nome", cadastro.nome);
                }
                else
                {
                    qry.Parameters.AddWithValue("@nome", cadastroLeitura.nome);
                }

                qry.ExecuteNonQuery();


                //instanciando classe dos comandos mysql
                MySqlCommand qry1 = new MySqlCommand(
                //o parametro é o nome da coluna no bcd
                "UPDATE tb_usuario SET nome_usuario = @nome_usuario WHERE id_usuario = @id_usuario", con);
                //comando mysql para adicionar informações em uma tabela

                //dando valor aos parametros utilizados no mysqlcommand
                qry1.Parameters.AddWithValue("@id_usuario", cadastroLeitura.id_usuario);

                if (cadastro.nome_usuario != null || cadastro.nome_usuario != "")
                {
                    qry1.Parameters.AddWithValue("@nome_usuario", cadastro.nome_usuario);
                }
                else
                {
                    qry1.Parameters.AddWithValue("@nome_usuario", cadastroLeitura.nome_usuario);
                }

                qry1.ExecuteNonQuery();

                con.Close();

                return Ok(new { mensagem = "Aprovado" });
            }
            catch (Exception exc)
            {
                if (con.State == System.Data.ConnectionState.Open)
                {
                    con.Close();
                }

                return Ok(new { mensagem = "Reprovado" });
            }
        }
    }
}

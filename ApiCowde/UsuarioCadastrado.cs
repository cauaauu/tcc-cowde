using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using MySql.Data.MySqlClient;


namespace ApiCowde
{
    public class UsuarioCadastrado
    {
        public string email { get; set; }

        public string senha { get; set; }

        public UsuarioCadastrado(string email, string senha)
        {
            this.email = email;
            this.senha = senha;
        }

        public UsuarioCadastrado()
        { }

        static MySqlConnection con = new MySqlConnection("Server=ESN509VMYSQL;Database=db_cowde;User id=aluno;password=Senai1234");

        //public static bool VerificarUsuarios(string email, string senha)
        //{
        //    bool status = false;
        //    try
        //    {
        //        con.Open();
        //        MySqlCommand qry = new MySqlCommand(
        //            "SELECT * FROM tb_usuario WHERE email = @email AND senha = @senha", con);
        //        qry.Parameters.AddWithValue("@email", email);
        //        qry.Parameters.AddWithValue("@senha", senha);

        //        UsuarioCadastrado uc = null;

        //        MySqlDataReader leitor = qry.ExecuteReader();

        //        while (leitor.Read())
        //        {
        //            uc = new UsuarioCadastrado(
        //                leitor["email"].ToString(),
        //                leitor["senha"].ToString()
        //              );
        //            status = true;
        //        }

        //        con.Close();
        //    }
        //    catch (Exception e)
        //    {
        //        if (con.State == System.Data.ConnectionState.Open)
        //            con.Close();
        //    }
        //    return status;
        //}
    }
}

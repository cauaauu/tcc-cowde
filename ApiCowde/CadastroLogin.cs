using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace ApiCowde
{
    public class CadastroLogin {

        //atributos da classe que fazem referência às informações do usuário 
        public int id_usuario { get; set; }
        public string nome { get; set; }
        public string nome_usuario { get; set; }
        public string email { get; set; }
        public string senha { get; set; }
        public int idade { get; set; }

        //construtor
        public CadastroLogin(string nome, string nome_usuario, string email, int idade, string senha)
        {
            this.nome = nome;
            this.nome_usuario = nome_usuario;
            this.email = email;
            this.idade = idade;
            this.senha = senha;
        }

        public CadastroLogin(int id_usuario, string nome, string nome_usuario, string email, int idade, string senha)
        {
            this.id_usuario = id_usuario;
            this.nome = nome;
            this.nome_usuario = nome_usuario;
            this.email = email;
            this.idade = idade;
            this.senha = senha;
        }

        public CadastroLogin()
        {
        }

        public CadastroLogin(string email)
        {
            this.email = email;
        }
    }
}

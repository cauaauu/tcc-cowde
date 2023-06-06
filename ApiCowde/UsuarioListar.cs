using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace ApiCowde
{
    public class UsuarioListar
    {
        public string email { get; set; }
        public string nome { get; set; }
        public string nome_usuario { get; set; }

        public UsuarioListar(string email, string nome, string nome_usuario)
        {
            this.email = email;
            this.nome = nome;
            this.nome_usuario = nome_usuario;
        }

        public UsuarioListar()
        { }
    }
}

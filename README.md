## Sobre
Projeto da disciplina de Paradigmas de Linguagens de Programação ([IN1007]) ministrada pelo professor Augusto Sampaio no semestre de 2017.1 no Centro de Informática da Universidade Federal de Pernambuco.

## Equipe
- Itanauã Barbosa (ifb2@cin.ufpe.br)
- Karla Silva (kmbs@cin.ufpe.br)

## Proposta
Persistência de objetos serializados para as linguagens de Orientação a Objetos 1.

## BNF
A BNF completa da linguagem de orientação a objetos 1 pode ser encontrada no [site da linguagem]. Para fins de uso no projeto, adicionamos a BNF existente a expressão abaixo:

IO ::= "write_file" "(" Expressao "," Expressao ")" | "read_file" "(" Id "," Expressao "," Expressao ")"

## Exemplo

```
{
      classe Cliente {
          string name = "",
          string cpf = "",
          string sexo = ""
          ;
          
          proc print() {
            write(this.name);
            write(this.sexo);
            write(this.cpf)
          },
          
          proc setName(string receivedName) {
            this.name := receivedName            
          },
          
          proc setCpf(string receivedCpf) {
            this.cpf := receivedCpf            
          },
          
          proc setSexo(string receivedSexo) {
            this.sexo := receivedSexo           
          }
      }
      ;

      {
        Cliente c1 := new Cliente,
        Cliente c2 := new Cliente
        ;
        
        write("Teste do write_file + read_file");
        
        c1.setName("Jose Silva");
        c1.setSexo("M");
        c1.setCpf("111.111.111-01");
        
        write_file(c1,"output.txt");
        read_file(c2,"output.txt",0);
        
        c2.print()
     }
   }
```

## Referências
1. [site da linguagem]
2. Site da Disciplina ([IN1007])
3. [Apresentacao do Projeto]

[IN1007]: <https://www.cin.ufpe.br/~in1007>
[site da linguagem]: <https://www.cin.ufpe.br/~in1007/linguagens/OrientadaObjetos1/orientadaObjetos1.html>

[Apresentacao do Projeto]: <https://github.com/karlambsilva/plp-project/blob/master/doc/IN1007%20-%20Apresentacao%202.pdf>

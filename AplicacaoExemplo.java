// https://refactoring.guru/design-patterns/strategy --> Passado para Java, traduzido

import java.util.Scanner;

/* 
 A interface Strategy declara operações comuns a todas as
 versões suportadas de algum algoritmo. O Context usa essa
 interface para chamar o algoritmo definido pelas Strategy concreta.
*/
interface Strategy {
    int executar(int a, int b);
}

/* 
 As Strategy concretra  implementam o algoritmo seguindo a
 interface base Strategy. Isso as torna intercambiáveis
 dentro do Context.
*/
class StrategyConcretaAdicao implements Strategy {
    public int executar(int a, int b) {
        return a + b;
    }
}

class StrategyConcretaSubtrair implements Strategy {
    public int executar(int a, int b) {
        return a - b;
    }
}

class StrategyConcretaMultiplicacao implements Strategy {
    public int executar(int a, int b) {
        return a * b;
    }
}

/* 
 O Context define a interface de interesse para os clientes.
*/
class Context {
    /* 
     O Context mantém uma referência para um dos objetos
     Strategy. O Context não sabe a classe concreta da
     estratégia. Ele deve funcionar com todas as estratégias
     via interface Strategy.
    */
    private Strategy strategy;

    /* 
     Geralmente o Context recebe uma Strategy através do
     construtor, e também fornece um setter para que a
     estratégia possa ser alterada em tempo de execução.
    */
    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    /* 
     O Context delega parte do trabalho para o objeto Strategy
     em vez de implementar múltiplas versões do algoritmo por
     conta própria.
    */
    public int executarStrategy(int a, int b) {
        return strategy.executar(a, b);
    }
}

/* 
 O código cliente escolhe uma Strategy concreta e a passa
 para o Context. O cliente deve estar ciente das diferenças
 entre as estratégias para tomar a decisão correta.
*/
public class AplicacaoExemplo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Context context = new Context();

        int primeiro, segundo, resultado;
        String operacao;

        System.out.println("Digite o primeiro número: ");
        primeiro = scanner.nextInt();

        System.out.println("Digite o segundo número: ");
        segundo = scanner.nextInt();

        System.out.println("Digite a operação (+, -, *): ");
        operacao = scanner.next();

        if (operacao.equals("+")) {
            context.setStrategy(new StrategyConcretaAdicao());
        }
        else if (operacao.equals("-")) {
            context.setStrategy(new StrategyConcretaSubtrair());
        }
        else if (operacao.equals("*")) {
            context.setStrategy(new StrategyConcretaMultiplicacao());
        } else {
            System.out.println("[ERRO]: Operação não suportada!");
            scanner.close();
        }

        // switch (operacao) {
        //     case "+":
        //         context.setStrategy(new StrategyConcretaAdicao());
        //         break;
        //     case "-":
        //         context.setStrategy(new StrategyConcretaSubtrair());
        //         break;
        //     case "*":
        //         context.setStrategy(new StrategyConcretaMultiplicacao());
        //         break;
        //     default:
        //         System.out.println("[ERRO]: Operação não suportada!");
        //         return;
        // }

        resultado = context.executarStrategy(primeiro, segundo);
        System.out.println("Resultado: " + resultado);

        scanner.close();
    }
}

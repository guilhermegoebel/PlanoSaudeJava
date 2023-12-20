package plano_saude;

import plano_saude.domain.cliente.Cliente;
import plano_saude.domain.cliente.ClienteService;
import plano_saude.domain.cliente.DadosCliente;
import plano_saude.domain.cliente.enums.Sexo;
import plano_saude.domain.cliente.enums.TipoPlano;
import plano_saude.domain.dependente.DadosDependente;
import plano_saude.domain.dependente.DependenteService;
import plano_saude.domain.dependente.enums.Parentesco;

import java.util.Scanner;

public class Main {
    private static final ClienteService clienteService = new ClienteService();
    private static final DependenteService dependenteService = new DependenteService();
    private static final Scanner scanner = new Scanner(System.in).useDelimiter("\n");

    public static void main(String[] args) {

        var option = showMenu();
        while (option != 10) {
                switch (option) {
                    case 1:
                        createClient();
                        break;
                    case 2:
                        getById();
                        break;
                    case 3:
                        getAll();
                        break;
                    case 4:
                        deleteClient();
                        break;
                    case 5:
                        updateClient();
                        break;
                    case 6:
                        getAllDependents();
                        break;
                    case 7:
                        createDependent();
                        break;
                    case 8:
                        deleteDependent();
                        break;
                    case 9:
                        updateDependent();
                        break;
                    default:
                        System.out.println("Invalid");
                        break;
                }

            option = showMenu();
        }

        System.out.println("Finishing app");
    }

    private static int showMenu() {
        System.out.println("""
                Escolha uma opção:
                
                1 - Cadastrar cliente
                2 - Exibir dados de um cliente
                3 - Listar todos os clientes
                4 - Excluir cliente
                5 - Atualizar cliente
                6 - Listar dependentes de um cliente
                7 - Cadastrar dependente
                8 - Deletar dependente
                9 - Atualizar dependente
                10 - Sair
                """);
        int option = scanner.nextInt();
        scanner.nextLine();
        return option;
    }

    public static void createClient(){

        System.out.println("Informe o nome do cliente: ");
        var nome = scanner.nextLine();

        Sexo sexo = readSexo();

        System.out.println("Informe o email do cliente: ");
        var email = scanner.nextLine();

        System.out.println("Informe o telefone do cliente: ");
        var telefone = scanner.nextLine();

        System.out.println("Informe a data de nascimento do cliente: ");
        var dt_nascimento = scanner.nextLine();

        System.out.println("Informe o local de nascimento do cliente: ");
        var local_nascimento = scanner.nextLine();

        System.out.println("Informe o endereço do cliente: ");
        var endereco = scanner.nextLine();

        System.out.println("Informe a empresa do cliente: ");
        var empresa = scanner.nextLine();

        TipoPlano tipo_plano = readTipoPlano();

        clienteService.createClient(new DadosCliente(nome, sexo, email, telefone, dt_nascimento, local_nascimento, endereco, empresa, tipo_plano));

        System.out.println("\nCliente cadastrado com sucesso!\n");
    }

    public static void getAll(){
            var clientes = clienteService.getAll();
            System.out.println("\nLista de clientes: \n");
            clientes.forEach(System.out::println);
            System.out.println("\n");
    }

    public static Sexo readSexo(){

        Sexo sexo = null;
        boolean valid = false;
        while (!valid) {
            System.out.println("Informe o sexo do cliente (1 - FEMININO, 2 - MASCULINO): ");
            int option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1:
                    sexo = Sexo.FEMALE;
                    valid = true;
                    break;
                case 2:
                    sexo = Sexo.MALE;
                    valid = true;
                    break;
                default:
                    System.out.println("Invalid");
            }
        }
        return sexo;
    }

    public static TipoPlano readTipoPlano(){

        TipoPlano tipo_plano = null;
        boolean valid = false;
        while (!valid) {
            System.out.println("Informe o tipo do plano do cliente (1 - OURO, 2 - PRATA, 3 - BRONZE): ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    tipo_plano = TipoPlano.OURO;
                    valid = true;
                    break;
                case 2:
                    tipo_plano = TipoPlano.PRATA;
                    valid = true;
                    break;
                case 3:
                    tipo_plano = TipoPlano.BRONZE;
                    valid = true;
                    break;
                default:
                    System.out.println("Invalid");
            }
        }
        return tipo_plano;
    }

    public static void getById(){

        System.out.println("\nInforme o id do cliente: ");
        var id = scanner.nextInt();
        Cliente cliente = clienteService.getById(id);
        System.out.println(cliente);
    }

    public static void deleteClient(){

        System.out.println("\nInforme o id do cliente que deseja deletar: ");
        var id = scanner.nextInt();
        clienteService.deleteClient(id);
        System.out.println("\nCliente deletado com sucesso!\n");
    }

    public static void updateClient(){

        System.out.println("Informe o id do cliente que deseja atualizar: ");
        var id = scanner.nextInt();
        System.out.println("""
                
                Informe qual dado do cliente você deseja atualizar:
                1 - Nome
                2 - Sexo
                3 - Email
                4 - Telefone
                5 - Data de Nascimento
                6 - Local de nascimento
                7 - Endereço
                8 - Empresa
                9 - Tipo de Plano
                """);
        var option = scanner.nextInt();
        scanner.nextLine();
        boolean valid = false;
        switch (option){
            case 1:
                System.out.println("Informe o nome do cliente: ");
                var nome = scanner.next();
                scanner.nextLine();
                clienteService.updateClient(id, option, nome);
                valid = true;
                break;
            case 2:
                Sexo sexo = readSexo();
                clienteService.updateClient(id, option, sexo.toString());
                valid = true;
                break;
            case 3:
                System.out.println("Informe o email do cliente: ");
                var email = scanner.nextLine();
                clienteService.updateClient(id, option, email);
                valid = true;
                break;
            case 4:
                System.out.println("Informe o telefone do cliente: ");
                var telefone = scanner.nextLine();
                clienteService.updateClient(id, option, telefone);
                valid = true;
                break;
            case 5:
                System.out.println("Informe a data de nascimento do cliente: ");
                var dt_nascimento = scanner.nextLine();
                clienteService.updateClient(id, option, dt_nascimento);
                valid = true;
                break;
            case 6:
                System.out.println("Informe o local de nascimento do cliente: ");
                var local_nascimento = scanner.nextLine();
                clienteService.updateClient(id, option, local_nascimento);
                valid = true;
                break;
            case 7:
                System.out.println("Informe o endereço do cliente: ");
                var endereco = scanner.nextLine();
                clienteService.updateClient(id, option, endereco);
                valid = true;
                break;
            case 8:
                System.out.println("Informe a empresa do cliente: ");
                var empresa = scanner.nextLine();
                clienteService.updateClient(id, option, empresa);
                valid = true;
                break;
            case 9:
                TipoPlano tipo_plano = readTipoPlano();
                clienteService.updateClient(id, option, tipo_plano.toString());
                valid = true;
                break;
            default:
                System.out.println("Opção Inválida!");
                break;
        }
        System.out.println("\n");
        if (valid){
            System.out.println("Cliente atualizado com sucesso!\n");
        }
    }

    public static void getAllDependents(){

        System.out.println("Informe o id do cliente para listar seus dependentes: ");
        var titular = scanner.nextInt();
        var dependentes = dependenteService.getAllDependents(titular);
        System.out.println("\nLista de dependentes: \n");
        dependentes.forEach(System.out::println);
        System.out.println("\n");
    }

    public static void createDependent(){

        boolean verify = false;
        var titular = 1;
        do{
            System.out.println("Informe o id do titular do dependente: ");
            titular = scanner.nextInt();
            verify = dependenteService.verifyClientId(titular);
            if(!verify){
                System.out.println("Não existe um cliente com esse id!");
            }
        }while(!verify);

        scanner.nextLine();
        System.out.println("Informe o nome do dependente: ");
        var nome = scanner.nextLine();

        Parentesco parentesco = readParentesco();

        dependenteService.createDependent(new DadosDependente(nome, titular, parentesco));

        System.out.println("\nDependente cadastrado com sucesso!\n");
    }

    public static Parentesco readParentesco(){

        Parentesco parentesco = null;
        boolean valid = false;
        while (!valid) {
            System.out.println("Informe o grau de parentesco do dependente com o cliente (1 - FILHO, 2 - FILHA, 3 - PAI, 4 - MÃE, 5 - AVÔ(a)): ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    parentesco = Parentesco.FILHO;
                    valid = true;
                    break;
                case 2:
                    parentesco = Parentesco.FILHA;
                    valid = true;
                    break;
                case 3:
                    parentesco = Parentesco.PAI;
                    valid = true;
                    break;
                case 4:
                    parentesco = Parentesco.MAE;
                    valid = true;
                    break;
                case 5:
                    parentesco = Parentesco.AVO;
                    valid = true;
                    break;
                default:
                    System.out.println("Invalid");
            }
        }
        return parentesco;
    }

    public static void deleteDependent(){

        System.out.println("\nInforme o id do dependente que deseja deletar: ");
        var id = scanner.nextInt();
        dependenteService.deleteDependent(id);
        System.out.println("\nDependente deletado com sucesso!\n");
    }

    public static void updateDependent(){

        System.out.println("Informe o id do dependente que deseja atualizar: ");
        var id = scanner.nextInt();
        System.out.println("""
                
                Informe qual dado do cliente você deseja atualizar:
                
                1 - Nome
                2 - Parentesco
                """);
        var option = scanner.nextInt();
        scanner.nextLine();
        boolean valid = false;
        switch (option){
            case 1:
                System.out.println("Informe o nome do dependente: ");
                var nome = scanner.nextLine();
                dependenteService.updateDependent(id, option, nome);
                valid = true;
                break;
            case 2:
                Parentesco parentesco  = readParentesco();
                dependenteService.updateDependent(id, option, parentesco.toString());
                valid = true;
                break;
            default:
                System.out.println("Opção Inválida!");
                break;
        }
        System.out.println("\n");
        if (valid){
            System.out.println("Dependente atualizado com sucesso!\n");
        }

    }

}
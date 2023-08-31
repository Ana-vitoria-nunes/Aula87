package org.example.view;

import org.example.model.InputUser;
import org.example.service.tabelaUsuario.UserService;

public class MenuView {
    private LoginUserView loginUserView = new LoginUserView();
    private LoginAdmView loginAdminView = new LoginAdmView();
    private InputUser inputUserModel = new InputUser();
    private UserService tableUserService = new UserService();

    public void start() {
        try {
            System.out.println("======================== Cinema SimCity ==========================");
            int option;
            do {
                printMenu();
                option = inputUserModel.readIntFromUser("Qual opção você deseja: ");

                switch (option) {
                    case 0:
                        System.out.println("Encerrando o programa...");
                        break;
                    case 1:
                        loginUserView.fazerLogin();
                        break;
                    case 2:
                        registerUser();
                        break;
                    case 3:
                        loginAdminView.fazerLogin();
                        break;
                    default:
                        System.out.println("Opção inválida, tente novamente!");
                }
            } while (option != 0);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void registerUser() {
        String cpf = inputUserModel.readStringFromUser("Digite o seu CPF sem caracteres especiais: ");
        String name = inputUserModel.readStringFromUser("Digite seu nome: ");
        String email = inputUserModel.readStringFromUser("Digite seu email: ");
        String senha = inputUserModel.readStringFromUser("Digite a senha: ");

        tableUserService.addUser(cpf, name, email, senha);
    }

    private void printMenu() {
        System.out.println("\n0. Sair | 1. Login Usuario | 2. Cadastrar Usuario | 3. Login Admin");
    }
}

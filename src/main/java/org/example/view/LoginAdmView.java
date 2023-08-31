package org.example.view;

import org.example.model.InputUser;
import org.example.model.UsuarioModel;
import org.example.service.tabelaFilmeFavorito.FavoriteMovieService;
import org.example.service.tabelaFilme.MovieService;
import org.example.service.tabelaUsuario.UserService;
import org.example.service.tabelaUsuario.ValidUserAdminService;

import java.util.Scanner;

public class LoginAdmView {
    private final Scanner scanner;
    private final UserService userService;
    private final MovieService filmeService;
    private final InputUser inputUser;
    private final ValidUserAdminService validUserAdmin;

    public LoginAdmView() {
        scanner = new Scanner(System.in);
        userService = new UserService();
        filmeService = new MovieService();
        inputUser=new InputUser();
        validUserAdmin=new ValidUserAdminService();
    }

    public void fazerLogin() {
        try {
            System.out.println("Digite seu nome:");
            String nome =scanner.nextLine();
            System.out.println("Digite a senha:");
            String senha=scanner.nextLine();

            if (validUserAdmin.isValidUserCredentials(nome, senha)) {
                System.out.println("========== Bem-Vindo " + nome + " ==========");
                int option;
                do {
                    menu();
                    option = inputUser.readIntFromUser("Qual opção você deseja: ");

                    switch (option) {
                        case 0:
                            new MenuView();
                            break;
                        case 1:
                            addMovie();
                            break;
                        case 2:
                            deleteMovies();
                            break;
                        case 3:
                            updateMovie();
                            break;
                        case 4:
                            deleteUser();
                            break;
                        default:
                            System.out.println("Opção inválida, tente novamente!");
                    }
                } while (option != 0);
            } else {
                System.out.println("Senha ou nome inválidos!");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void addMovie() {
        int code = inputUser.readIntFromUser("Codigo do Filme: ");
        String title = inputUser.readStringFromUser("Nome do filme: ");
        String image = inputUser.readStringFromUser("Url da imagem do filme: ");
        String description = inputUser.readStringFromUser("Descrição do filme: ");
        String gender = inputUser.readStringFromUser("Qual o gênero do filme: ");
        filmeService.addMovie(code, title, image, description, gender);
    }

    private void deleteMovies() {
        int code = inputUser.readIntFromUser("Qual o código do Filme: ");
        filmeService.deleteMovie(code);
    }

    private void updateMovie() {
        int code = inputUser.readIntFromUser("Digite o código do Filme que deseja atualizar: ");
        String title = inputUser.readStringFromUser("Novo nome do filme: ");
        String image = inputUser.readStringFromUser("Nova Url da imagem do filme: ");
        String description = inputUser.readStringFromUser("Nova descrição do filme: ");
        String gender = inputUser.readStringFromUser("Qual o gênero do filme: ");

        filmeService.updateMovie(code, title, image, description, gender);
    }

    private void deleteUser() {
        userService.listUsers();
        int id = inputUser.readIntFromUser("Qual o id do usuário que deseja deletar: ");
        userService.deleteUser(id);
    }

    private void menu() {
        System.out.println("\n0. Menu Principal |" +
                " 1. Add Filmes |" +
                " 2. Deletar Filmes |" +
                " 3. Modificar Fimes |" +
                " 4. Deletar Usuário");
    }
}

package org.example.view;
import org.example.model.InputUser;
import org.example.service.tabelaFilme.MovieService;
import org.example.service.tabelaFilme.ValidDataBaseMovieService;
import org.example.service.tabelaFilmeFavorito.FavoriteMovieService;
import org.example.service.tabelaUsuario.UserService;
import org.example.service.tabelaUsuario.ValidDataBaseUserService;

public class LoginUserView {
    private ValidDataBaseMovieService validDataBaseMovieService = new ValidDataBaseMovieService();
    private ValidDataBaseUserService validDataBase = new ValidDataBaseUserService();
    private InputUser inputUserModel = new InputUser();
    private MovieService tableMovieService = new MovieService();
    private UserService tableUserService = new UserService();
    private FavoriteMovieService tableFavoriteMovieService = new FavoriteMovieService();

    public void fazerLogin() {
        String name = inputUserModel.readStringFromUser("Digite seu nome:");
        String password = inputUserModel.readStringFromUser("Digite a senha:");

        if (validDataBase.isValidUserCredentials(name, password)) {
            System.out.println("========== Bem-Vindo " + name + " ==========");
            validDataBase.userInfoByAlias(name);
            int option;
            do {
                menu();
                option = inputUserModel.readIntFromUser("Qual opção você deseja: ");

                switch (option) {
                    case 0:
                        new MenuView();
                        break;
                    case 1:
                        availableMovies();
                        break;
                    case 2:
                        availableFavoriteMovies();
                        break;
                    case 3:
                        addFavoriteMovies();
                        break;
                    case 4:
                        deleteFavoriteMovies();
                        break;
                    case 5:
                        updateData();
                        break;
                    default:
                        System.out.println("Opção inválida, tente novamente!");
                }
            } while (option != 0);
        } else {
            System.out.println("Senha ou nome inválidos!");
        }
    }

    private void availableMovies() {
        tableMovieService.listMovies();
    }

    private void availableFavoriteMovies() {
        tableFavoriteMovieService.listFavoriteMovies();
    }

    private void addFavoriteMovies() {
        String title = inputUserModel.readStringFromUser("Qual o nome do filme: ");
        String image = inputUserModel.readStringFromUser("Qual a URL da imagem do filme: ");

        if (!validDataBaseMovieService.isValidMovieToAddToFavorite(title)) {
            System.out.println("Filme não encontrado, tente adicionar outro!");

        } else {
            tableFavoriteMovieService.addFavoriteMovie(title, image);
        }
    }

    private void deleteFavoriteMovies() {
        int id = inputUserModel.readIntFromUser("Qual o ID do filme que deseja deletar: ");
        tableFavoriteMovieService.deleteFavoriteMovie(id);
    }

    private void updateData() {
        int id = inputUserModel.readIntFromUser("Qual o ID da sua conta: ");
        String email = inputUserModel.readStringFromUser("Qual o novo email: ");
        String senha = inputUserModel.readStringFromUser("Qual a nova senha: ");
        tableUserService.updateUser(id, email, senha);
    }

    private void menu() {
        System.out.println("""

                0. Menu Principal |\s
                1. Filmes disponíveis |\s
                2. Filmes Favoritos |\s
                3. Adicionar aos favoritos |\s
                4. Deletar dos favoritos |\s
                5. Atualizar dados"""
        );
    }
}


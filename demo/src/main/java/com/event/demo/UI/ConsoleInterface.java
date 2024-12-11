package com.event.demo.UI;

import com.event.demo.auth.AuthenticationRequest;
import com.event.demo.auth.AuthenticationResponse;
import com.event.demo.auth.AuthenticationService;
import com.event.demo.auth.RegisterRequest;
import com.event.demo.evento.Event;
import com.event.demo.evento.EventService;
import com.event.demo.evento.RegisterEventRequest;
import com.event.demo.user.Role;

import java.util.List;
import java.util.Scanner;

public class ConsoleInterface {

    private final AuthenticationService authService;
    private final EventService eventService;
    private final Scanner scanner = new Scanner(System.in);

    public ConsoleInterface(AuthenticationService authService, EventService eventService) {
        this.authService = authService;
        this.eventService = eventService;
    }

    public void start() {
        while (true) {
            System.out.println("1. Registar");
            System.out.println("2. Autenticar");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (choice) {
                case 1:
                    register();
                    break;
                case 2:
                    authenticate();
                    break;
                case 3:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void register() {
        System.out.print("Nome: ");
        String firstname = scanner.nextLine();
        System.out.print("Sobrenome: ");
        String lastname = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String password = scanner.nextLine();

        // Você pode adicionar mais campos conforme necessário.

       // RegisterRequest request = new RegisterRequest(firstname, lastname, email, password, Role.USER);
       // AuthenticationResponse response = authService.register(request);
       // System.out.println("Registo concluído! Token de Acesso: " + response.getAccessToken());
    }

    private void authenticate() {
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String password = scanner.nextLine();

        AuthenticationRequest request = new AuthenticationRequest(email, password);
        AuthenticationResponse response = authService.authenticate(request);
        System.out.println("Autenticação bem-sucedida! Token de Acesso: " + response.getAccessToken());
        manageEvents();
    }

    private void manageEvents() {
        while (true) {
            System.out.println("1. Criar Evento");
            System.out.println("2. Listar Eventos");
            System.out.println("3. Atualizar Evento");
            System.out.println("4. Excluir Evento");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (choice) {
                case 1:
                    createEvent();
                    break;
                case 2:
                    listEvents();
                    break;
                case 3:
                    updateEvent();
                    break;
                case 4:
                    deleteEvent();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void createEvent() {
        System.out.println("\n--- Criar Evento ---");
        System.out.print("Digite o nome do evento: ");
        String name = scanner.nextLine();
        System.out.print("Digite a data do evento (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        System.out.print("Digite a localização do evento: ");
        String location = scanner.nextLine();


        RegisterEventRequest request = new RegisterEventRequest(name,"god",date,location,"me",null );
       // eventService.createEvent(request, null);
        System.out.println("Evento criado com sucesso: " + name + " em " + location + " na data " + date);
    }

    private void listEvents() {
        System.out.println("\n--- Lista de Eventos ---");
        List<Event> ev = eventService.getAllEvents();
        if (ev.isEmpty()) {
            System.out.println("Não há eventos para exibir.");
        } else {
            System.out.println("Lista de Eventos:");
            System.out.println("-------------------------------------");
            for (Event event : ev) {
                System.out.println("ID: " + event.getId());
                System.out.println("Título: " + event.getName());
                System.out.println("Data: " + event.getDateTime());
                System.out.println("Localização: " + event.getLocation());
                System.out.println("Organizer: " + event.getOrganizer());
                System.out.println("-------------------------------------");
            }
        }
    }

    private void updateEvent() {
        System.out.println("\n--- Atualizar Evento ---");
        System.out.print("Digite o ID do evento para atualizar: ");
        String eventId = scanner.nextLine();

        System.out.print("Digite o novo nome do evento: ");
        String name = scanner.nextLine();
        System.out.print("Digite a nova data do evento (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        System.out.print("Digite a nova localização do evento: ");
        String location = scanner.nextLine();

        
        System.out.println("Evento atualizado com sucesso: " + name + " em " + location + " na data " + date);
    }

    private void deleteEvent() {
        System.out.println("\n--- Apagar Evento ---");
        System.out.print("Digite o ID do evento para apagar: ");
        String eventId = scanner.nextLine();

        // Aqui deve ir o código para apagar o evento com o ID fornecido
        System.out.println("Evento com ID " + eventId + " foi apagado.");
    }

}

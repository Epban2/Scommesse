public class Main {
    public static void main(String[] args) {
        Cliente cliente1 = new Cliente("Nicola", "Zalewski");
        Cliente cliente2 = new Cliente("Sandro", "Tonali");
        Cliente cliente3 = new Cliente("Nicolò", "Fagioli");
        Cliente cliente4 = new Cliente("Gianluigi", "Buffon");
        GestioneClienti gestioneClienti = new GestioneClienti();
        gestioneClienti.AggiungiCliente(cliente1);
        gestioneClienti.AggiungiCliente(cliente2);
        gestioneClienti.AggiungiCliente(cliente3);
        gestioneClienti.AggiungiCliente(cliente4);
        GuiScommesse guiScommesse = new GuiScommesse(gestioneClienti);
    }
}
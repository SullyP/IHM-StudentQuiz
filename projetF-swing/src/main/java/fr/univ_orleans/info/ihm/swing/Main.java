package fr.univ_orleans.info.ihm.swing;

public class Main {

    public static final String HOST = "127.0.0.1";
    public static void main(String[] args) {
        /*try {
            Registry registry = LocateRegistry.getRegistry(HOST, 9345);
            IFacadeDAO facadeDAO = (IFacadeDAO) registry.lookup(IFacadeDAO.SERVICE_NAME);
            //facadeDAO.creerEntite("test");
        } catch (Exception e) {
            MyLogger.getLogger().logp(Level.SEVERE, Main.class.getName(), "main", "ServiceRemote exception", e);
        }*/
        System.out.println("Hello");
    }
}

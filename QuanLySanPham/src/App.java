import javax.swing.JFrame;

public class App extends JFrame {
    public App() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 300);
        setTitle("Hello, World!");
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) throws Exception {
        new App();
    }
}

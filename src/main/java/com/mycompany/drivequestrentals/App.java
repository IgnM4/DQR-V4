package com.mycompany.drivequestrentals;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * Clase principal JavaFX App que gestiona el cambio de vistas y lanza la escena inicial.
 */
public class App extends Application {

    private static Scene scene;
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;

        // Inicializar la escena con la vista del menú principal
        scene = new Scene(loadFXML("menuPrincipal"), 800, 600);
        agregarEstilos(scene);
        configurarVentana(stage);
        stage.show();
    }

    /**
     * Cambia la raíz del Scene actual a un nuevo FXML.
     * @param fxml nombre del archivo FXML (sin extensión)
     * @throws IOException si no se puede cargar el archivo
     */
    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    /**
     * Carga un archivo FXML desde la ruta /fxml/.
     * @param fxml nombre del archivo sin extensión
     * @return nodo raíz del FXML cargado
     * @throws IOException si hay un error en la carga
     */
    private static Parent loadFXML(String fxml) throws IOException {
        URL fxmlURL = App.class.getResource("/fxml/" + fxml + ".fxml");
        if (fxmlURL == null) {
            throw new IOException("No se encontró el archivo FXML: " + fxml);
        }
        return new FXMLLoader(fxmlURL).load();
    }

    /**
     * Aplica hoja de estilos CSS principal a la escena.
     * @param scene escena a la que se le aplicará el estilo
     */
    private static void agregarEstilos(Scene scene) {
        URL cssURL = App.class.getResource("/styles/primary.css");
        if (cssURL != null) {
            scene.getStylesheets().add(cssURL.toExternalForm());
        }
    }

    /**
     * Configura parámetros generales de la ventana principal.
     * @param stage ventana principal de la aplicación
     */
    private static void configurarVentana(Stage stage) {
        stage.setTitle("Drive Questrentals");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.getIcons().add(new Image("file:src/main/resources/com/mycompany/drivequestrentals/assets/logo.png"));
    }

    /**
     * Devuelve el Stage principal de la aplicación.
     * @return ventana principal
     */
    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch();
    }
}

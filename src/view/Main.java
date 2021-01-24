package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Model;
import model.interpreter.ParserAutoPilot;
import model.interpreter.commands.DisconnectCommand;
import viewModel.ViewModel;

public class Main extends Application {

    @Override
    public void start(Stage mainStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));
        Parent parent = loader.load();
        Controller ctrl = loader.getController();

        ViewModel viewModel = new ViewModel();
        Model model = new Model();
        model.addObserver(viewModel);
        viewModel.setModel(model);
        viewModel.addObserver(ctrl);
        ctrl.setViewModel(viewModel);

        StageHandling(mainStage, parent, model);
    }

    private void StageHandling(Stage mainStage, Parent parent, Model model) throws Exception{
        mainStage.setTitle("Tal & Ido Flight control box");
        mainStage.setScene(new Scene(parent));
        mainStage.show();

        mainStage.setOnCloseRequest(event -> {
            DisconnectCommand command = new DisconnectCommand();
            String[] disconnect = {""};
            command.Execute(disconnect);
            ParserAutoPilot.exe.interrupt();
            model.stopAll();
            System.out.println("bye");
        });
    }

    public static void main(String[] args) { launch(args); }
}
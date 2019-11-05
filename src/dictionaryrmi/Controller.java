package dictionaryrmi;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private  ServerAPI serverAPI;

    @FXML
    protected TextField query;
    @FXML
    protected TextField keyword;
    @FXML
    protected TextArea def;
    @FXML
    protected TextArea response;
    @FXML
    protected Button search_bt;
    @FXML
    protected Button submit_bt;
    @FXML
    protected Button delete_bt;
    @FXML
    protected Label submit_status;
    @FXML
    protected Label submit_status2;

    @FXML
    protected Label connectionLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            this.serverAPI=ServerAPI.getServerAPI();
            connectionLabel.setText("Connected");
        }catch (Exception e){
            connectionLabel.setText("Not Connected");
        }
                response.setEditable(false);
        delete_bt.setDisable(true);
        delete_bt.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String key=query.getText();
                String res=serverAPI.delete(key);
                submit_status2.setText(res);           
                clearBox();
            }
        });

        submit_bt.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String key = keyword.getText();
                String definition= def.getText();
                if(!key.isEmpty() && !definition.isEmpty()){
                    String res=serverAPI.add(key,definition);
                    submit_status.setText(res);
                    clearForm();
                }else{
                    submit_status.setText("fill the form correctly");
                }
                
            }
        });

        search_bt.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String q = query.getText();
                String res = serverAPI.search(q);
                response.setText(res);
                submit_status2.setText("...");
                delete_bt.setDisable(false);
            }
        });
    }
    private void clearBox(){
        query.setText("");
        response.setText("");
        delete_bt.setDisable(true);
    }
    private void clearForm(){
        keyword.setText("");
        def.setText("");
    }
}
package com.lifeisle.ui;


import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;


public class BrowserLauncher extends Application {
    @Override public void start(Stage stage) {
        // create the scene
        stage.setTitle("Web View");

        Parameters parameters = getParameters();
        String url = parameters.getRaw().get(0);

        Scene scene = new Scene(new Browser(url), 750, 500, Color.web("#666970"));
        stage.setScene(scene);

//        scene.getStylesheets().add("webviewsample/BrowserToolbar.css");
        stage.show();
    }

    public static void launchBrowser(String... args){
        launch(args);
    }



    public static void main(String[] args) {
        launch("http://www.baidu.com");
    }
}



class Browser extends Region {

    final WebView browser = new WebView();
    final WebEngine webEngine = browser.getEngine();


    public Browser(String url) {        //apply the styles
//        getStyleClass().add("browser");
//         load the web page
        webEngine.load(url);
        //add the web view to the scene
        getChildren().add(browser);

    }
    private Node createSpacer() {
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        return spacer;
    }

    @Override protected void layoutChildren() {
        double w = getWidth();
        double h = getHeight();
        layoutInArea(browser,0,0,w,h,0, HPos.CENTER, VPos.CENTER);
    }

    @Override protected double computePrefWidth(double height) {
        return 750;
    }

    @Override protected double computePrefHeight(double width) {
        return 500;
    }
}
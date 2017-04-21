/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tester;


import java.io.File;


import java.io.IOException;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.List;
import java.util.prefs.Preferences;

import javafx.fxml.FXMLLoader;
import javafx.collections.*;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;


/**
 *
 * @author vshershnov
 */
public class MainApp1 {
    
    /**
     * Данные, в виде наблюдаемого списка tasks.
     */
    private ObservableList<Task1> taskData = FXCollections.observableArrayList();
    
    /**
     * Конструктор
     */
    public MainApp1() {
        // В качестве образца добавляем некоторые данные
        TaskListReader1 tskRdr = new TaskListReader1();
        List<Task1> taskList = tskRdr.getGroupedTaskList();
        Iterator<Task1> itg = taskList.iterator();
        while (itg.hasNext()) {
                taskData.add(itg.next());  
            }
    }
    
    /**
     * Возвращает данные в виде наблюдаемого списка адресатов.
     * @return
     */
    public ObservableList<Task1> getTaskData() {
        return taskData;
    }

    
    /**
    * Загружает информацию об адресатах из указанного файла.
    * Текущая информация об адресатах будет заменена.
    * 
    * @param file
    */
    public void loadTaskDataFromFile(File file) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(TaskListWrapper1.class);
            Unmarshaller um = context.createUnmarshaller();

            // Чтение XML из файла и демаршализация.
            TaskListWrapper1 wrapper = (TaskListWrapper1) um.unmarshal(file);

            taskData.clear();
            taskData.addAll(wrapper.getTasks());

        } catch (Exception e) { // catches ANY exception
            System.out.println("tester.MainApp1.loadTaskDataFromFile(File file) \n" +"JAXBException.....Could not load data from file:\n");
           
        }
    }

    /**
    * Сохраняет текущую информацию об адресатах в указанном файле.
    * 
    * @param file
    */
    public void saveTaskDataToFile() {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(TaskListWrapper1.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        
            // Обёртываем наши данные об адресатах.
            TaskListWrapper1 wrapper = new TaskListWrapper1();
            wrapper.setTasks(taskData);
            
            
            // Маршаллируем и сохраняем XML в файл.
            //m.marshal(wrapper, System.out);
            m.marshal(wrapper, new File("tasks.xml"));
        
        } catch (JAXBException e) { // catches ANY exception
            
            System.out.println("tester.MainApp1.saveTaskDataToFile()\n" +"JAXBException.....Could not save data to file:\n");

            
        }
        
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       MainApp1 MainApp = new MainApp1();
       MainApp.saveTaskDataToFile();
    }
    
}

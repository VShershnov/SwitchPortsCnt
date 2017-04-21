/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tester;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import javafx.collections.ObservableList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * Вспомогательный класс для обёртывания списка адресатов.
 * Используется для сохранения списка адресатов в XML.
 * 
 * @author vshersnov
 */
@XmlRootElement(name = "tasks")
public class TaskListWrapper1 {
    private List tasks;

    @XmlElements({ @XmlElement(name = "task", type = Task1.class) })
    //@XmlElement(name = "task1", type = Task1.class)
    //@XmlElement(name = "task")
    public List getTasks() {
        return tasks;
    }
    
    
    public void setTasks(List tasks) {
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        return "TaskListWrapper1{" + "tasks=" + tasks + '}';
    }
    
    
}

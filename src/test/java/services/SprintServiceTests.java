/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.przemo.projectmanagementweb.services.ITaskService;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import services.mocks.Storage;
import services.mocks.TaskServiceMock;

/**
 *
 * @author Przemo
 */
public class SprintServiceTests {
    
    private final ITaskService taskService = new TaskServiceMock();
    private final SprintServiceStub sprintService = new SprintServiceStub();
    
    public SprintServiceTests() {
        sprintService.setTaskService(taskService);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void closeableSprintTest(){
        assertTrue(!sprintService.isSprintCloseable(Storage.getCurrentSprint()));
    }
}

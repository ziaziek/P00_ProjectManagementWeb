/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.przemo.projectmanagementweb.helpers.CodeGenerator;
import com.przemo.projectmanagementweb.services.UserManagementService;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Przemo
 */
public class UserManagementTests {
    
    static class UM extends UserManagementService{
        
        public String generateCode(final String sp){
            return CodeGenerator.generateCode(sp);
        }
    }
    private static UM userManagementService;
    
    public UserManagementTests() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        userManagementService = new UM();
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
    public void generateCodeTest(){
        String code = userManagementService.generateCode("dsfdssdg");
        Assert.assertNotNull(code);
        Assert.assertEquals(36, code.length());
        Assert.assertTrue(!code.contains(" "));
        System.out.println(code);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msi.main;

import com.msi.login.LoginForm;
import javax.swing.UIManager;

/**
 *
 * @author MSI
 */
public class Main {
    public static void main(String[] args) {
         try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
              //  UIManager.setLookAndFeel("com.jtattoo.plaf.fast.FastLookAndFeel");
               // UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");
               UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
              //  UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");
                
//                
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        LoginForm l=new LoginForm();
        l.setVisible(true);
    }
}

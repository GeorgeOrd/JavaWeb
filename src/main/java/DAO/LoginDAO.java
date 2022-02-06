/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DB.ConnectionDB;
import Model.UsuarioModel;

import java.sql.ResultSet;

public class LoginDAO {

    int response = 0;
    String sql = "";
    ResultSet rs = null;
    ConnectionDB con = new ConnectionDB();

    public int verificarCredenciales(UsuarioModel usm) throws Exception {
        sql = "SELECT COUNT(*) as CANT  FROM usuario WHERE nickname='" + usm.getNickname() + "' AND contrasenia='" + usm.getContrasenia() + "'";
        rs = con.executeQuery(sql);

        while (rs.next()) {
            response =rs.getInt("cant");
      
        }

        return response;
    }
    
     public int verificarRol(UsuarioModel usm) throws Exception {
        sql = "SELECT id_persona as rol FROM usuario WHERE nickname='" + usm.getNickname() + "' AND contrasenia='" + usm.getContrasenia() + "'";
        rs = con.executeQuery(sql);

        while (rs.next()) {
            response =rs.getInt("rol");
        }

        return response;
    }
}

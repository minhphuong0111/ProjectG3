/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author WIN8.1X64
 */
public class kiemtrachinhta {
    public boolean kiemtraso(String a){
        Pattern pattern = Pattern.compile("\\d*"); 
        Matcher matcher = pattern.matcher(a); 
        if (matcher.matches()) { 
            return true;
        } else { 
             return false;
        }
    }
}

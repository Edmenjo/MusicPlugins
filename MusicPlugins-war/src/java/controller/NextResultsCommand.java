/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

/**
 *
 * @author zuzu
 */
public class NextResultsCommand extends FrontCommand{

    
    private HttpSession session;
    @Override
    public void process() throws ServletException, IOException {
        session = request.getSession();
        calculateNextIndex();
        try {
            forward("/web/vstmanagement.jsp");
        } catch (ServletException | IOException ex) {
            Logger.getLogger(NextResultsCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void calculateNextIndex() {
        Integer index = (Integer) session.getAttribute("indextofind");
        Long indexes = (Long) session.getAttribute("indexes");
        index += 3;
        int indexMax = indexes.intValue() * 3;

        if (index > indexMax) {
            index = indexMax;
        }
        session.setAttribute("indextofind", index);

    }
    
    
}

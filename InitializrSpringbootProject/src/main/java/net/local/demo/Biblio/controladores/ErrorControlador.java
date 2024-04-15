/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.local.demo.Biblio.controladores;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author pc
 */
@Controller
public class ErrorControlador implements ErrorController {

    
    /*
    El controlador está diseñado desde el error hasta la presentación dentro del método.
    Spring permite definir la página de error en una carpeta en la que busca los errores y se pueden crear las paginas por
    cada codigo de error con extensión html. Sin embargo, por simplicidad, se integró en el controlador actual toda la
    información de presentación en este.
    */
    @RequestMapping("/error")
    @ResponseBody
    public String handleError(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("jakarta.servlet.error.status_code");
        Exception exception = (Exception) request.getAttribute("jakarta.servlet.error.exception");
        return String.format("<!DOCTYPE html><html><head>\n"
                + "<title>Control de Errores</title>\n"
                + "<meta charset=\"UTF-8\" />\n"
                + "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n"
                + "<link rel=\"stylesheet\" href=\"/css/default.css\" />\n"
                + "</head><body><h1>Página de error</h1><h2>Status code: <b>%s</b></h2>"
                + "<h3>Mensaje de Excepción: <b>%s</b></h3><body></html>",
                statusCode, exception == null ? "N/A" : exception.getMessage());
    }

    public String getErrorPath() {
        return "/error";
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package net.local.demo.Biblio.controladores;

import java.util.List;
import net.local.demo.Biblio.entidades.Editorial;
import net.local.demo.Biblio.excepciones.MiExcepcion;
import net.local.demo.Biblio.servicios.EditorialServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author pc
 */
/**
 * <h1>EditorialControlador</h1>
 * Spring MVC controller that handles HTTP requests related to editorial management. This class manages endpoints for listing
 * editorials, registering new editorials, and modifying existing ones. It uses the EditorialServicio to perform business
 * logic operations and interacts with the view layer through ModelMap. The controller is mapped to the "/view/editorial" URL
 * path.
 *
 * <p>
 * Key functionalities:</p>
 * <ul>
 * <li>Handles GET requests for listing editorials and displaying registration forms.</li>
 * <li>Manages POST requests for saving or updating editorials.</li>
 * <li>Provides error handling for exceptions during editorial operations.</li>
 * </ul>
 *
 * <p>
 * Annotations:</p>
 * <ul>
 * <li>{@link Controller} - Marks this class as a Spring MVC controller.</li>
 * <li>{@link RequestMapping} - Maps HTTP requests to handler methods.</li>
 * <li>{@link Autowired} - Injects the editorialServicio dependency.</li>
 * </ul>
 *
 * <p>
 * Notes:</p>
 * <ul>
 * <li>Uses {@link ModelMap} to pass data to views.</li>
 * <li>Handles both creation and modification of editorials via the {@link EditorialServicio}.</li>
 * </ul>
 */
@Controller
@RequestMapping("/view/editorial")
public class EditorialControlador {

    @Autowired
    EditorialServicio editorialServicio;

    public String page(Model model) {
        model.addAttribute("attribute", "value");
        return "view.name";
    }

    ///
/// Maneja solicitudes HTTP GET para listar editoriales.
/// 
/// **Endpoint:** `GET /lista`  
/// **Descripción:**  
/// Obtiene todas las editoriales desde el servicio, las agrega al modelo bajo la clave `"editoriales"`,  
/// y retorna la vista `"editorial_list.html"`.  
/// 
/// **Model Attributes:**  
/// - `editoriales`: Lista de objetos `Editorial` recuperados desde el servicio.  
/// 
/// **Excepciones:**  
/// - Lanza excepciones si ocurre un error en `editorialServicio.listarEditoriales()`.  
/// 
/// @see EditorialServicio#listarEditoriales()  
/// @since 0.01  
///
@GetMapping("/lista")
    public String listar(ModelMap modelo) {
        List<Editorial> editoriales = editorialServicio.listarEditoriales();
        modelo.addAttribute("editoriales", editoriales);
        return "editorial_list.html";
    }
 
    ///
/// Muestra el formulario de registro de una nueva editorial.  
/// 
/// **Endpoint:** `GET /registra-editorial`  
/// **Descripción:**  
/// Retorna la vista `"editorial_registro.html"` sin datos pre-cargados.  
/// 
/// **Uso:**  
/// Este método se utiliza para mostrar el formulario vacío de registro.
    /// @param modelo a cargar datos
    /// @return página de registro sin datos.
///
@GetMapping("/registra-editorial")
    public String registroEditorial(ModelMap modelo) {
        return "editorial_registro.html";
    }

    ///
/// Carga los datos de una editorial existente para edición.  
/// 
/// **Endpoint:** `GET /registra-editorial/{id}`  
/// **Descripción:**  
/// Si se proporciona un `id`, recupera la editorial desde el servicio y agrega sus atributos al modelo:  
/// - `id`: Identificador de la editorial.  
/// - `nombre`: Nombre de la editorial.  
/// 
/// **Parámetros:**  
/// - `@PathVariable(required = false) Long id`: Identificador opcional de la editorial.  
/// 
/// **Uso:**  
/// - Sin `id`: Muestra un formulario vacío.  
/// - Con `id`: Muestra datos pre-cargados para edición.  
/// 
/// **Referencia:**  
/// El endpoint sigue patrones comunes de APIs REST para gestión de recursos.
    /// @param id que se usa para buscar en el servicio
    /// @param modelo que se cargará con la id del servicio.
    /// @return la página para modelo cargado.
///
@GetMapping("/registra-editorial/{id}")
    public String registraEditorial(@PathVariable(required = false) Long id, ModelMap modelo) {
        Editorial editorial = editorialServicio.getOne(id);
        modelo.addAttribute("id", editorial.getId());
        modelo.addAttribute("nombre", editorial.getNombre());
        return "editorial_registro.html";
    }

    /**
     * Registra o modifica una editorial según el ID proporcionado. Si el ID es nulo, crea una nueva editorial; de lo
     * contrario, actualiza la existente.
     *
     * @param id Identificador de la editorial (opcional). Si es nulo, se crea una nueva editorial.
     * @param nombre Nombre de la editorial (requerido).
     * @param modelo {@link ModelMap} para agregar atributos al modelo, como mensajes de éxito o error.
     * @return Nombre de la vista "editorial_registro.html" para renderizar la respuesta.
     * @throws MiExcepcion Si ocurre un error durante la creación o modificación de la editorial.
     */
    @PostMapping("/registro")
    public String registra(@RequestParam(required = false) Long id, @RequestParam String nombre, ModelMap modelo) throws MiExcepcion {
        Editorial editorial = null;
        try {
            if (id == null) {
                editorial = editorialServicio.crearEditorial(nombre);
                modelo.addAttribute("exito", "Guardado con Id: " + editorial.getId());
            } else {
                editorialServicio.modificarEditorial(id, nombre);
                modelo.addAttribute("exito", "Se modificó Id: " + id);
            }

        } catch (MiExcepcion e) {
            modelo.addAttribute("id", id);
            modelo.addAttribute("nombre", nombre);
            modelo.addAttribute("error", "Hubo un inconveniente:" + e.getMessage());
        }
        return "editorial_registro.html";
    }
}

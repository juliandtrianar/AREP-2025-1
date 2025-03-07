import com.example.accessingdatajpa.controlador.EntityControlador;
import com.example.accessingdatajpa.modelo.EntityDatos;
import com.example.accessingdatajpa.service.EntityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class EntityControladorTest {

    /**
     * Inyección de dependencias para el controlador bajo prueba.
     * Se utiliza para inicializar el controlador con un servicio simulado.
     */
    @InjectMocks
    private EntityControlador entityControlador;

    /**
     * Mock del servicio que maneja las entidades.
     * Permite simular el comportamiento del servicio durante las pruebas.
     */
    @Mock
    private EntityService entityService;

    /**
     * Mock del modelo utilizado en las vistas.
     * Facilita la interacción con los atributos del modelo en las pruebas.
     */
    @Mock
    private Model model;

    /**
     * Configuración inicial de las pruebas antes de cada método.
     * Se inicializan los mocks antes de ejecutar las pruebas.
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Prueba para verificar la visualización del formulario.
     * Se espera que el método retorne el nombre de la vista del formulario
     * y que se agregue un atributo "entityDatos" al modelo.
     */
    @Test
    public void testMostrarFormularioHtml() {
        String viewName = entityControlador.mostrarFormulario(model);

        // Verifica que se retorna el nombre de la vista correcta.
        assertEquals("formulario", viewName);

        // Verifica que se agrega un atributo "entityDatos" al modelo.
        verify(model).addAttribute(eq("entityDatos"), any(EntityDatos.class));
    }

    /**
     * Prueba para agregar una nueva entidad.
     * Se simula la creación de una entidad y se verifica que se redirige correctamente
     * y que se llama al servicio para agregar la entidad.
     */
    @Test
    public void testAgregarEntidad() {
        EntityDatos entidad = new EntityDatos("villa", 100, 1000000, "casa amoblada");

        // Verifica la redirección tras agregar la entidad.
        String redirect = entityControlador.agregarEntidad(entidad);
        assertEquals("redirect:/agregar", redirect);

        // Captura la entidad que se pasó al servicio.
        ArgumentCaptor<EntityDatos> entityCaptor = ArgumentCaptor.forClass(EntityDatos.class);
        verify(entityService).AgregarEntidad(entityCaptor.capture());

        // Verifica que la entidad capturada tiene los valores esperados.
        EntityDatos entidadCapturada = entityCaptor.getValue();
        assertEquals("villa", entidadCapturada.getAddress());
        assertEquals(100, entidadCapturada.getSize());
        assertEquals(1000000, entidadCapturada.getPrice());
        assertEquals("casa amoblada", entidadCapturada.getDescription());
    }

    /**
     * Prueba para obtener todas las entidades en formato JSON.
     * Se simula la obtención de una lista de entidades y se verifica que se
     * retornen el número correcto de entidades.
     */
    @Test
    public void testObtenerEntidades() {
        List<EntityDatos> entidades = Arrays.asList(new EntityDatos(), new EntityDatos());
        when(entityService.obtenerdatos()).thenReturn(entidades);

        List<EntityDatos> result = entityControlador.obtenerEntidadesJson();

        // Verifica que se obtienen dos entidades.
        assertEquals(2, result.size());

        // Verifica que el servicio fue llamado para obtener las entidades.
        verify(entityService).obtenerdatos();
    }

    /**
     * Prueba para eliminar una entidad por ID.
     * Se espera que la respuesta sea 200 OK tras la eliminación
     * y se verifica que se llama al servicio para eliminar la entidad.
     */
    @Test
    public void testEliminarEntidad() {
        Long id = 1L;
        ResponseEntity<Void> response = entityControlador.eliminarEntidad(id);

        // Verifica que la respuesta es 200 OK después de la eliminación.
        assertEquals(ResponseEntity.ok().build(), response);

        // Verifica que el servicio de entidad fue llamado para eliminar la entidad.
        verify(entityService).eliminarEntidad(id);
    }

    /**
     * Prueba para actualizar una entidad existente.
     * Se simula que el servicio devuelve la entidad existente,
     * se actualiza la entidad y se verifica que la entidad devuelta
     * y que se llama al servicio para agregar la entidad.
     */
    @Test
    public void testActualizarEntidad() {
        Long id = 1L;
        EntityDatos entidad = new EntityDatos();
        entidad.setPrice(100);
        entidad.setSize(50);
        entidad.setAddress("villadelrio");

        // Simular que el servicio devuelve la entidad existente.
        when(entityService.obtenerPorId(id)).thenReturn(entidad);

        ResponseEntity<EntityDatos> response = entityControlador.actualizarEntidad(id, entidad);

        // Verifica que la entidad devuelta es la correcta.
        assertEquals(entidad, response.getBody());

        // Verifica que se llama al servicio para agregar la entidad.
        verify(entityService).AgregarEntidad(entidad);
    }
}


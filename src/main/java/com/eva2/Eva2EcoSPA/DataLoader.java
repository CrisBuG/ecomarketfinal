package com.eva2.Eva2EcoSPA;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.eva2.Eva2EcoSPA.model.Usuario;
import com.eva2.Eva2EcoSPA.Repository.EnvioRepository;
import com.eva2.Eva2EcoSPA.Repository.ListaRepository;
import com.eva2.Eva2EcoSPA.Repository.PedidoRepository;
import com.eva2.Eva2EcoSPA.model.Envio;
import com.eva2.Eva2EcoSPA.model.Lista;
import com.eva2.Eva2EcoSPA.model.Pedido;
import com.eva2.Eva2EcoSPA.model.Producto;
import com.eva2.Eva2EcoSPA.model.Reporte;
import com.eva2.Eva2EcoSPA.Repository.UsuarioRepository;
import com.eva2.Eva2EcoSPA.Repository.ProductoRepository;
import com.eva2.Eva2EcoSPA.Repository.ReporteRepository;

import net.datafaker.Faker;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private EnvioRepository envioRepository;

    @Autowired
    private ListaRepository listaRepository;

    @Autowired
    private ReporteRepository reporteRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        
        // Verificar si ya hay datos para evitar duplicados
        if (usuarioRepository.count() > 0) {
            System.out.println("⚠️ Los datos ya existen, saltando la carga inicial.");
            return;
        }
        
        System.out.println("🚀 Iniciando carga de datos de prueba...");
        
        Faker faker = new Faker();
        Random random = new Random();
        
        try {
            // 1. Crear usuarios primero
            System.out.println("📝 Creando usuarios...");
            for (int i = 0; i < 20; i++) {
                Usuario usuario = new Usuario();
                usuario.setNombre(faker.name().fullName());
                usuario.setEmail(faker.internet().emailAddress());
                usuario.setPassword(faker.internet().password(8, 16));
                usuario.setRun(faker.number().digits(8));
                usuario.setRol(faker.options().option("CLIENTE", "ADMIN", "REPARTIDOR"));
                usuarioRepository.save(usuario);
            }
            usuarioRepository.flush(); // Forzar escritura a BD

            // 2. Crear productos
            System.out.println("📦 Creando productos...");
            for (int i = 0; i < 20; i++) {
                Producto producto = new Producto();
                producto.setNombre(faker.commerce().productName());
                producto.setDescripcion(faker.lorem().sentence());
                producto.setPrecio(Double.parseDouble(faker.commerce().price()));
                producto.setStock(faker.number().numberBetween(1, 100));
                producto.setCategoria(faker.commerce().department());
                productoRepository.save(producto);
            }
            productoRepository.flush();

            // Obtener listas de entidades creadas
            List<Usuario> usuarios = usuarioRepository.findAll();
            List<Producto> productos = productoRepository.findAll();

            if (usuarios.isEmpty() || productos.isEmpty()) {
                throw new RuntimeException("Error: No se pudieron crear usuarios o productos");
            }

            // 3. Crear pedidos con relaciones
            System.out.println("🛒 Creando pedidos...");
            for (int i = 0; i < 20; i++) {
            Pedido pedido = new Pedido();
            pedido.setCantidad(faker.number().numberBetween(1, 10));
            pedido.setFechaPedido(new Date());
            pedido.setEstado(faker.options().option("PENDIENTE", "ENVIADO", "ENTREGADO"));
            pedido.setTotal(Double.parseDouble(faker.commerce().price(10.0, 1000.0)));
    
    // Correct method names based on the entity fields
            pedido.setUsuario(usuarios.get(random.nextInt(usuarios.size())));
            pedido.setProducto(productos.get(random.nextInt(productos.size())));
    
            pedidoRepository.save(pedido);
            }
            pedidoRepository.flush();

            // Obtener pedidos para crear envíos
            List<Pedido> pedidos = pedidoRepository.findAll();

            // 4. Crear envíos con relaciones a pedidos
            System.out.println("🚚 Creando envíos...");
            for (int i = 0; i < 15; i++) {
                Envio envio = new Envio();
                envio.setDireccionEntrega(faker.address().fullAddress());
                envio.setEstado(faker.options().option("PREPARACION", "EN_CAMINO", "ENTREGADO"));
                envio.setFechaEnvio(new Date());
                envio.setFechaEstimadaEntrega(faker.date().future(7, TimeUnit.DAYS));
                envio.setTransportista(faker.company().name());
                
                // Asignar pedido aleatorio
                envio.setPedido(pedidos.get(random.nextInt(pedidos.size())));
                
                envioRepository.save(envio);
            }
            envioRepository.flush();

            // 5. Crear listas con relaciones a usuarios y productos
            System.out.println("📋 Creando listas...");
            for (int i = 0; i < 20; i++) {
                Lista lista = new Lista();
                lista.setTipo(faker.options().option("COMPRA", "DESEOS", "FAVORITOS"));
                lista.setCantidad(faker.number().numberBetween(1, 5));
                
                // Asignar usuario y producto aleatorios
                lista.setUsuario(usuarios.get(random.nextInt(usuarios.size())));
                lista.setProducto(productos.get(random.nextInt(productos.size())));
                
                listaRepository.save(lista);
            }
            listaRepository.flush();

            // 6. Crear reportes
            System.out.println("📊 Creando reportes...");
            for (int i = 0; i < 10; i++) {
                Reporte reporte = new Reporte();
                reporte.setTipo(faker.options().option("VENTAS", "STOCK", "ENTREGAS"));
                reporte.setFechaGeneracion(new Date());
                reporte.setDatos(faker.lorem().paragraph());
                reporte.setFormato(faker.options().option("PDF", "EXCEL", "CSV"));
                reporteRepository.save(reporte);
            }
            reporteRepository.flush();
            
            System.out.println("✅ Datos de prueba cargados exitosamente:");
            System.out.println("   - " + usuarios.size() + " usuarios");
            System.out.println("   - " + productos.size() + " productos");
            System.out.println("   - " + pedidoRepository.count() + " pedidos");
            System.out.println("   - " + envioRepository.count() + " envíos");
            System.out.println("   - " + listaRepository.count() + " listas");
            System.out.println("   - " + reporteRepository.count() + " reportes");
            
        } catch (Exception e) {
            System.err.println("❌ Error al cargar datos: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
}
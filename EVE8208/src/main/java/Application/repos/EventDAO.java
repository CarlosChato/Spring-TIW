package Application.repos;

import Application.mysqlControllers.Event;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Clase de acceso a persistencia (DAO) para la clase Computer y que está basada en CrudRepository
 *
 * Un crud repository tiene una serie de métodos ya disponibles que permiten operar sobre entidades:
 * save( Entity) -> Hacer persistente una instancia de entidad en BBDD
 * findById()
 * findAll()
 * count()
 * delete(Entity) -> Eliminar de la base de datos..
 * exist...
 */

public interface EventDAO extends CrudRepository<Event, Long> {
    // En el cuerpo de esta interfaz debemos incorporar todas las queries en forma de métodos que necesitemos realizar sobre nuestras tablas:
    // Hay 2 formas de autogenerar la implementación de los métodos que realizan queries en un CrudRepository:
    //  1) Especificar queries por nombrado (camelCase)  findByNombreAtributoAndNombreAtributo2( ...)
    //  2) Especificar queries explícitamente por JPQL
    List<Event> findAll();
}

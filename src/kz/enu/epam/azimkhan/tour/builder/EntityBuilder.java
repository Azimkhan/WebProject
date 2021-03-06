package kz.enu.epam.azimkhan.tour.builder;

import kz.enu.epam.azimkhan.tour.entity.Entity;
import kz.enu.epam.azimkhan.tour.exception.BuildException;

import javax.servlet.ServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Abstract entity from request builder
 */
public abstract class EntityBuilder<T extends Entity> {

    /**
     * Build entity from request
     *
     *
     * @param map @return
     * @throws BuildException
     */
    public abstract T build(Map<String, String[]> map) throws BuildException;
}

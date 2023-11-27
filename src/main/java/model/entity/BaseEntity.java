/**
 * This package contains entity classes that represent various data models.
 * These entities are used to define the structure of data stored in the application's database.
 */
package model.entity;

import java.io.Serializable;

/**
 * An interface that serves as the base for all entity classes.
 * Entities that implement this interface are expected to be serializable.
 */
public interface BaseEntity extends Serializable {
}

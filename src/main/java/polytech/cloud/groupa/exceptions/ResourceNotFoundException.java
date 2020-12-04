package polytech.cloud.groupa.exceptions;

/**
 * This custom exception is used when a Position or a User cannot be retrieved form persistence.
 *
 * @author Ewald Janin
 */
public class ResourceNotFoundException extends Exception {

    /**
     * Construct a ResourceNotFoundException with specified parameters
     *
     * @param resourceName the name of the resource that cannot be found (Position or User)
     * @param fieldName the field that was specified as a filter for looking for the resource
     * @param fieldValue the value of the field that was specified as a filter for looking for the resource
     */
    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("Can't find resource %s whose %s 's value(s) is '%s'", resourceName, fieldName, fieldValue));
    }
}

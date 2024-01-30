package com.example.code.domain.exception;

public class DatabaseException extends RuntimeException {

  private static final long serialVersionUID = -563485510711657205L;

  private final Errors error;
  private final String operacion;

  private DatabaseException(String operacion, Errors error, Throwable cause) {
    super(error.getDescripcion(), cause);
    this.error = error;
    this.operacion = operacion;
  }

  /**
   * Method to encapsulate the error handling of this exception.
   *
   * @param error, enum error code with an error description.
   * @param cause, error origins
   * @throws DatabaseException
   */
  public static void throwException(String operacion, Errors error, Throwable cause) {
    throw new DatabaseException(operacion, error, cause);
  }

  public String getSimpleDescriptionError() {
    return new StringBuffer(error.getCodigoError() + ": " + getMessage())
        .append(" when invoking: " + operacion)
        .toString();
  }

  public enum Errors {
    GENERIC_ERROR("Generic error from database"),
    DATA_INTEGRITY_VIOLATION("Error inserting on database");

    private String descripcion;

    Errors(String descripcion) {
      this.descripcion = descripcion;
    }

    public String getCodigoError() {
      return this.name();
    }

    public String getDescripcion() {
      return this.descripcion;
    }
  }
}

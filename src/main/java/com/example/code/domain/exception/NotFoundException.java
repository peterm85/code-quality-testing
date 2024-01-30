package com.example.code.domain.exception;

public class NotFoundException extends RuntimeException {

  private static final long serialVersionUID = -5895639208606544290L;
  private final Errors error;
  private final String operacion;

  public NotFoundException(String operacion, Errors error, Throwable cause) {
    super(error.getDescripcion(), cause);
    this.error = error;
    this.operacion = operacion;
  }

  /**
   * Method to encapsulate the error handling of this exception.
   *
   * @param error, enum error code with an error description.
   * @param cause, error origins
   * @throws NotFoundException
   */
  public static void throwException(String operacion, Errors error, Throwable cause) {
    throw new NotFoundException(operacion, error, cause);
  }

  public String getSimpleDescriptionError() {
    return new StringBuffer(error.getCodigoError() + ": " + getMessage())
        .append(" when invoking: " + operacion)
        .toString();
  }

  public enum Errors {
    USER_NOT_FOUND("User does not exists");

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

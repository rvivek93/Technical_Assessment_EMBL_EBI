package com.embl_ebi.assessment.exception;

/**
 * @author Vivek Rajendran
 *
 */
public class ResourceNotFoundException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public ResourceNotFoundException(String msg) {
    super(msg);
  }
}
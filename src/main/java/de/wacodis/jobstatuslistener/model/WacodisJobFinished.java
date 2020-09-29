package de.wacodis.jobstatuslistener.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import de.wacodis.jobstatuslistener.model.ProductDescription;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.UUID;
import org.joda.time.DateTime;
import java.io.Serializable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * message to indicate that a job execution finished succesfully 
 */
@ApiModel(description = "message to indicate that a job execution finished succesfully ")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-09-29T13:37:51.239+02:00[Europe/Berlin]")

public class WacodisJobFinished  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("wacodisJobIdentifier")
  private UUID wacodisJobIdentifier = null;

  @JsonProperty("executionFinished")
  private DateTime executionFinished = null;

  @JsonProperty("productDescription")
  private ProductDescription productDescription = null;

  @JsonProperty("singleExecutionJob")
  private Boolean singleExecutionJob = false;

  public WacodisJobFinished wacodisJobIdentifier(UUID wacodisJobIdentifier) {
    this.wacodisJobIdentifier = wacodisJobIdentifier;
    return this;
  }

  /**
   * wacodis job identifer (from WacodisJobDefinition, not wps job identifier!) 
   * @return wacodisJobIdentifier
  **/
  @ApiModelProperty(required = true, value = "wacodis job identifer (from WacodisJobDefinition, not wps job identifier!) ")
  @NotNull

  @Valid

  public UUID getWacodisJobIdentifier() {
    return wacodisJobIdentifier;
  }

  public void setWacodisJobIdentifier(UUID wacodisJobIdentifier) {
    this.wacodisJobIdentifier = wacodisJobIdentifier;
  }

  public WacodisJobFinished executionFinished(DateTime executionFinished) {
    this.executionFinished = executionFinished;
    return this;
  }

  /**
   * timestamp when message was published 
   * @return executionFinished
  **/
  @ApiModelProperty(required = true, value = "timestamp when message was published ")
  @NotNull

  @Valid

  public DateTime getExecutionFinished() {
    return executionFinished;
  }

  public void setExecutionFinished(DateTime executionFinished) {
    this.executionFinished = executionFinished;
  }

  public WacodisJobFinished productDescription(ProductDescription productDescription) {
    this.productDescription = productDescription;
    return this;
  }

  /**
   * Get productDescription
   * @return productDescription
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public ProductDescription getProductDescription() {
    return productDescription;
  }

  public void setProductDescription(ProductDescription productDescription) {
    this.productDescription = productDescription;
  }

  public WacodisJobFinished singleExecutionJob(Boolean singleExecutionJob) {
    this.singleExecutionJob = singleExecutionJob;
    return this;
  }

  /**
   * indicates if finished wacodis job is single execution job (SingleJobExecutionEvent) 
   * @return singleExecutionJob
  **/
  @ApiModelProperty(required = true, value = "indicates if finished wacodis job is single execution job (SingleJobExecutionEvent) ")
  @NotNull


  public Boolean getSingleExecutionJob() {
    return singleExecutionJob;
  }

  public void setSingleExecutionJob(Boolean singleExecutionJob) {
    this.singleExecutionJob = singleExecutionJob;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WacodisJobFinished wacodisJobFinished = (WacodisJobFinished) o;
    return Objects.equals(this.wacodisJobIdentifier, wacodisJobFinished.wacodisJobIdentifier) &&
        Objects.equals(this.executionFinished, wacodisJobFinished.executionFinished) &&
        Objects.equals(this.productDescription, wacodisJobFinished.productDescription) &&
        Objects.equals(this.singleExecutionJob, wacodisJobFinished.singleExecutionJob);
  }

  @Override
  public int hashCode() {
    return Objects.hash(wacodisJobIdentifier, executionFinished, productDescription, singleExecutionJob);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class WacodisJobFinished {\n");
    
    sb.append("    wacodisJobIdentifier: ").append(toIndentedString(wacodisJobIdentifier)).append("\n");
    sb.append("    executionFinished: ").append(toIndentedString(executionFinished)).append("\n");
    sb.append("    productDescription: ").append(toIndentedString(productDescription)).append("\n");
    sb.append("    singleExecutionJob: ").append(toIndentedString(singleExecutionJob)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}


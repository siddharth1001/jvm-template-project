package com.example.app

import io.dropwizard.Configuration
import javax.validation.constraints.NotEmpty
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class AppConfiguration : Configuration() {
    @NotEmpty
    private var template: String = ""

    @NotEmpty
    private var defaultName = "Stranger"

    @JsonProperty
    fun getTemplate(): String {
        return template
    }

    @JsonProperty
    fun setTemplate(template: String) {
        this.template = template
    }

    @JsonProperty
    fun getDefaultName(): String {
        return defaultName
    }

    @JsonProperty
    fun setDefaultName(name: String) {
        defaultName = name
    }
}

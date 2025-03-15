package dev.abhi.userservice.userservice.models;

import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;


public enum SessionStatus  {
    ACTIVE,
    ENDED,
}

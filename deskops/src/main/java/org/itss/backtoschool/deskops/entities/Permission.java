package org.itss.backtoschool.deskops.entities;

/**
 * Application-level permissions. These must match permissions configured in Auth0.
 * Role mappings in Auth0:
 * - EMPLOYEE (default): VIEW_SEATS, RESERVE_SEAT, CANCEL_RESERVATION, VIEW_TRAFFIC, VIEW_WEATHER
 * - HR: All EMPLOYEE permissions + VIEW_REPORTS (to analyze booking history)
 * - ADMIN: All permissions (full system access)
 */
public enum Permission {
    // Employee permissions (default role)
    VIEW_SEATS,
    RESERVE_SEAT,
    CANCEL_RESERVATION,
    VIEW_TRAFFIC,
    VIEW_WEATHER,

    // HR permissions (employee management)
    VIEW_REPORTS,

    // Admin permissions (full control)
    CREATE_SEAT,
    UPDATE_SEAT,
    DELETE_SEAT,
    MANAGE_ROOMS,
    MANAGE_USERS,
    ASSIGN_ROLE
}

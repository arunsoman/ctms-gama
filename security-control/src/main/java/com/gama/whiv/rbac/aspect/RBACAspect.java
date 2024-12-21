package com.gama.whiv.rbac.aspect;

import com.gama.whiv.rbac.annotation.RequiresRole;
import com.gama.whiv.rbac.exception.UnauthorizedException;
import com.gama.whiv.rbac.service.AuthorizationService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RBACAspect {

    @Autowired
    private AuthorizationService authorizationService;

    @Before("@annotation(requiresRole)")
    public void checkRole(JoinPoint joinPoint, RequiresRole requiresRole) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String methodName = methodSignature.getMethod().getName();
        String role = requiresRole.value();
        if (!authorizationService.hasAccess(role)) {
            throw new UnauthorizedException("User does not have the required role: " + role + " to access method: " + methodName);
        }
    }
}

package com.gama.whiv.rbac.aspect;

import com.gama.whiv.rbac.annotation.NeedPermission;
import com.gama.whiv.rbac.exception.UnauthorizedException;
import com.gama.whiv.rbac.service.AuthorizationService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PermissionAspect {

    @Autowired
    private AuthorizationService authorizationService;

    @Around("@annotation(needPermission)")
    public Object checkPermission(ProceedingJoinPoint joinPoint, NeedPermission needPermission) throws Throwable {
        String permission = needPermission.value();
        if (!authorizationService.hasAccess(permission)) {
            throw new UnauthorizedException("User does not have the required permission: " + permission);
        }
        return joinPoint.proceed();
    }
}

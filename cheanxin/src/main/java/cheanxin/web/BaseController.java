package cheanxin.web;

import cheanxin.domain.PostAuthority;
import cheanxin.domain.User;
import cheanxin.enums.Authority;
import cheanxin.exceptions.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * 所有Controller基类，用以捕获特定异常，让返回的错误提示信息友好
 * Created by Jawinton on 16/12/22.
 */
@RestController
public class BaseController {

    /**
     * 日志记录器
     */
    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    @ExceptionHandler(InvalidArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse invalidArgument(InvalidArgumentException e) {
        return e.getErrorResponse();
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse notFound(ResourceNotFoundException e) {
        return e.getErrorResponse();
    }

    @ExceptionHandler(ResourceConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse conflict(ResourceConflictException e) {
        return e.getErrorResponse();
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse unauthorized(UnauthorizedException e) {
        return e.getErrorResponse();
    }

    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse forbidden(ForbiddenException e) {
        return e.getErrorResponse();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse illegalArgument(IllegalArgumentException e) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.getReasonPhrase(), e.getMessage());
    }

    @ExceptionHandler(IllegalAccessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse illegalAccessException(IllegalAccessException e) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.getReasonPhrase(), e.getMessage());
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse unknownException(Throwable e) {
        logger.error(e.getMessage());
        return new ErrorResponse(HttpStatus.BAD_REQUEST.getReasonPhrase(), "Server internal error.");
    }

    /**
     * @Valid错误判断
     * @param errors
     */
    protected void assertFieldError(Errors errors) {
        if (!errors.hasErrors()) {
            return;
        }
        ObjectError firstError = errors.getAllErrors().get(0);
        String errorMessage = firstError.getDefaultMessage();
        if (firstError instanceof FieldError) {
            errorMessage = ((FieldError) firstError).getField() + ": " + errorMessage;
        }
        Assert.isNull(firstError, errorMessage);
    }

    /**
     * Check product city authority.
     * @param user
     * @param cityId
     * @return
     */
    protected Set<Long> getReadAuthorityCities(User user, long cityId, Authority[] authorities) {
        Collection<? extends PostAuthority> postAuthorities = user.getPostAuthorities();
        Assert.isTrue(postAuthorities != null && !postAuthorities.isEmpty(), "User authorities error.");
        Set<Long> cityIds = null;
        for (Authority authority : authorities) {
            boolean isSingleCity = false;
            for (PostAuthority postAuthority : postAuthorities) {
                if (!authority.name().equals(postAuthority.getAuthority())) {
                    continue;
                }
                if (!postAuthority.getCityCheck()) {
                    if (cityId <= 0L) {
                        // 如果拥有所有城市的权限，直接放回
                        return null;
                    } else {
                        isSingleCity = true;
                    }
                }
                Assert.isTrue(user.getCityIds() != null && !user.getCityIds().isEmpty(), "User cities error.");
                if (cityId <= 0L) {
                    cityIds = user.getCityIds();
                }
                if (user.getCityIds().contains(cityId)) {
                    isSingleCity = true;
                }
            }
            if (isSingleCity) {
                cityIds = new HashSet<>(1);
                cityIds.add(cityId);
            }
        }
        if (cityIds != null) {
            return cityIds;
        }

        throw new ForbiddenException("Forbidden.");
    }
}
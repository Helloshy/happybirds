package com.airparking.apms.server.handler;

//import static io.netty.buffer.Unpooled.copiedBuffer;
//import static io.netty.handler.codec.http.HttpHeaders.Names.CONNECTION;
//import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_LENGTH;
//import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_TYPE;
//import static io.netty.handler.codec.http.HttpHeaders.Names.COOKIE;
//import static io.netty.handler.codec.http.HttpHeaders.Names.SET_COOKIE;
//import static io.netty.handler.codec.http.HttpHeaders.Values.CLOSE;
//import static io.netty.handler.codec.http.HttpHeaders.Values.KEEP_ALIVE;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.airparking.apms.api.key.entity.SecretKey;
import com.airparking.apms.api.key.service.SecretKeyService;
import com.airparking.apms.server.RequestBuilder;
import com.airparking.apms.server.RequestMapping;
import com.airparking.apms.server.ResponseCode;
import com.airparking.apms.server.ServiceException;
import com.airparking.apms.server.ServiceRequest;
import com.airparking.apms.server.ServiceResponse;
import com.airparking.apms.server.spring.SpringContext;
import com.airparking.core.anno.Authorize;
import com.airparking.core.anno.RequiredParams;
import com.airparking.core.comm.utils.JsonUtils;
import com.airparking.core.comm.utils.PropertiesUtils;
import com.airparking.core.comm.utils.Signature;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.shade.io.netty.buffer.ByteBuf;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.shade.io.netty.channel.Channel;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.shade.io.netty.channel.ChannelFuture;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.shade.io.netty.channel.ChannelFutureListener;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.shade.io.netty.channel.ChannelHandlerContext;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.shade.io.netty.channel.SimpleChannelInboundHandler;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.shade.io.netty.handler.codec.http.DefaultFullHttpResponse;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.shade.io.netty.handler.codec.http.FullHttpResponse;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.shade.io.netty.handler.codec.http.HttpContent;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.shade.io.netty.handler.codec.http.HttpHeaders;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.shade.io.netty.handler.codec.http.HttpMethod;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.shade.io.netty.handler.codec.http.HttpObject;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.shade.io.netty.handler.codec.http.HttpRequest;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.shade.io.netty.handler.codec.http.HttpResponseStatus;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.shade.io.netty.handler.codec.http.HttpVersion;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.shade.io.netty.handler.codec.http.LastHttpContent;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.shade.io.netty.handler.codec.http.QueryStringDecoder;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.shade.io.netty.handler.codec.http.cookie.Cookie;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.shade.io.netty.handler.codec.http.cookie.ServerCookieDecoder;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.shade.io.netty.handler.codec.http.cookie.ServerCookieEncoder;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.shade.io.netty.handler.codec.http.multipart.Attribute;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.shade.io.netty.handler.codec.http.multipart.DefaultHttpDataFactory;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.shade.io.netty.handler.codec.http.multipart.DiskAttribute;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.shade.io.netty.handler.codec.http.multipart.DiskFileUpload;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.shade.io.netty.handler.codec.http.multipart.FileUpload;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.shade.io.netty.handler.codec.http.multipart.HttpData;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.shade.io.netty.handler.codec.http.multipart.HttpDataFactory;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.shade.io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.shade.io.netty.handler.codec.http.multipart.InterfaceHttpData;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.shade.io.netty.util.CharsetUtil;
import static com.aliyun.openservices.shade.com.alibaba.rocketmq.shade.io.netty.buffer.Unpooled.copiedBuffer;

//import io.netty.buffer.ByteBuf;
//import io.netty.channel.Channel;
//import io.netty.channel.ChannelFuture;
//import io.netty.channel.ChannelFutureListener;
//import io.netty.channel.ChannelHandlerContext;
//import io.netty.channel.SimpleChannelInboundHandler;
//import io.netty.handler.codec.http.DefaultFullHttpResponse;
//import io.netty.handler.codec.http.FullHttpResponse;
//import io.netty.handler.codec.http.HttpContent;
//import io.netty.handler.codec.http.HttpHeaders;
//import io.netty.handler.codec.http.HttpMethod;
//import io.netty.handler.codec.http.HttpObject;
//import io.netty.handler.codec.http.HttpRequest;
//import io.netty.handler.codec.http.HttpResponseStatus;
//import io.netty.handler.codec.http.HttpVersion;
//import io.netty.handler.codec.http.LastHttpContent;
//import io.netty.handler.codec.http.QueryStringDecoder;
//import io.netty.handler.codec.http.cookie.Cookie;
//import io.netty.handler.codec.http.cookie.ServerCookieDecoder;
//import io.netty.handler.codec.http.cookie.ServerCookieEncoder;
//import io.netty.handler.codec.http.multipart.Attribute;
//import io.netty.handler.codec.http.multipart.DefaultHttpDataFactory;
//import io.netty.handler.codec.http.multipart.DiskAttribute;
//import io.netty.handler.codec.http.multipart.DiskFileUpload;
//import io.netty.handler.codec.http.multipart.FileUpload;
//import io.netty.handler.codec.http.multipart.HttpData;
//import io.netty.handler.codec.http.multipart.HttpDataFactory;
//import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
//import io.netty.handler.codec.http.multipart.InterfaceHttpData;
//import io.netty.util.CharsetUtil;

/**
 * Created by ryan on 12/25/15.
 */
public class ServiceHandler extends SimpleChannelInboundHandler<HttpObject> {
  private static final Logger logger = LoggerFactory.getLogger(ServiceHandler.class);

  private static final long DEFAULT_MAX_FILE_LENGTH = 1024 * 1024;

  private static final boolean IS_PRODUCTION = false;

  private static final boolean IGNORE_SIGN = true;

  private HttpRequest request;

  private boolean readingChunks;

  private Map<String, Object> parameters;

  private HttpData partialContent;

  private static final HttpDataFactory factory = new DefaultHttpDataFactory(
      DefaultHttpDataFactory.MINSIZE); // Disk if size exceed

  private HttpPostRequestDecoder decoder;

  private Set<Cookie> cookies;

  private long maxFileLength = 0;

  private String uri;
  
  public static final String CONTENT_LENGTH = "Content-Length";
  public static final String CONNECTION = "Connection";
  public static final String CONTENT_TYPE = "Content-Type";
  public static final String COOKIE = "Cookie";
  public static final String CLOSE = "close";
  public static final String KEEP_ALIVE = "keep-alive";
  public static final String SET_COOKIE = "Set-Cookie";

  static {
    DiskFileUpload.deleteOnExitTemporaryFile = true; // should delete file
    // on exit (in normal
    // exit)
    DiskFileUpload.baseDirectory = PropertiesUtils.get("fileupload.directory"); // system
                                                                                // temp
                                                                                // directory
    DiskAttribute.deleteOnExitTemporaryFile = true; // should delete file on
    // exit (in normal exit)
    DiskAttribute.baseDirectory = PropertiesUtils.get("fileupload.directory"); // system
                                                                               // temp
                                                                               // directory
  }

  @Override
  protected void channelRead0(ChannelHandlerContext channelHandlerContext, HttpObject httpObject)
      throws Exception {
    if (httpObject instanceof HttpRequest) {
      HttpRequest request = this.request = (HttpRequest) httpObject;
      parameters = new HashMap<>();

      uri = request.getUri();
      // logger.info(uri);
      // parameters.put("uri", uri);
      // serviceRequest = new ServiceRequest();
      // serviceRequest.setPath(RequestPath.split(uri));
      // no handle for http headers
      // for (Map.Entry<String, String> entry : request.headers()) {
      // }

      String value = request.headers().get(COOKIE);
      if (value == null) {
        cookies = Collections.emptySet();
      } else {
        cookies = ServerCookieDecoder.STRICT.decode(value);
      }

      QueryStringDecoder queryStringDecoder = new QueryStringDecoder(uri);
      Map<String, List<String>> uriAttributes = queryStringDecoder.parameters();

      for (Map.Entry<String, List<String>> entry : uriAttributes.entrySet()) {
        List<String> val = entry.getValue();
        if (val != null && val.size() == 1) {
          parameters.put(entry.getKey(), val.get(0));
        } else {
          parameters.put(entry.getKey(), val);
        }
      }

      if (HttpMethod.GET.equals(request.getMethod())) {
        writeResponse(channelHandlerContext.channel(), handleRequest(parameters));
        return;
      }

      try {
        decoder = new HttpPostRequestDecoder(factory, request);
      } catch (HttpPostRequestDecoder.ErrorDataDecoderException e) {
        logger.error(e.getMessage());
        writeResponse(channelHandlerContext.channel(), new ServiceResponse(e));
        channelHandlerContext.channel().close();
        return;
      }

      readingChunks = HttpHeaders.isTransferEncodingChunked(request);
      logger.debug("Is Chunked:" + readingChunks);
      if (decoder != null) logger.debug("IsMultipart:" + decoder.isMultipart());
    }

    if (decoder != null) {
      if (httpObject instanceof HttpContent) {
        HttpContent chunk = (HttpContent) httpObject;

        try {
          decoder.offer(chunk);
        } catch (HttpPostRequestDecoder.ErrorDataDecoderException e) {
          logger.error("Read post data error.", e);
          writeResponse(channelHandlerContext.channel(), new ServiceResponse(e));
          channelHandlerContext.channel().close();
          return;
        }

        readHttpDataChunkByChunk(parameters);

        if (chunk instanceof LastHttpContent) {
          writeResponse(channelHandlerContext.channel(), handleRequest(parameters));
          readingChunks = false;
          reset();
        }
      }
    } else {
      writeResponse(channelHandlerContext.channel(), new ServiceResponse(ResponseCode.SUCCESS));
    }
  }

  private void reset() {
    request = null;
    decoder.destroy();
    decoder = null;
  }

  private ServiceResponse handleRequest(Map<String, Object> parameters) {
    if (uri.contains("favicon") || "/".equals(uri))
      return new ServiceResponse(ResponseCode.SUCCESS);

    try {
      long start = System.currentTimeMillis();

      logger.info("=====开始执行=====");
      logger.info("请求URI:" + request.getUri());
      logger.info("参数:" + JsonUtils.toJson(parameters));

      // 1. build service request and verify
      if (parameters == null || parameters.size() == 0)
        throw new ServiceException(ResponseCode.MISS_REQUIRED);

      ServiceRequest serviceRequest = null;
      try {
        serviceRequest = RequestBuilder.getInstance(parameters).addRequestUri(uri).build();
      } catch (Exception e) {
        if (e.getCause() != null && e.getCause() instanceof ServiceException) {
          throw (ServiceException) e.getCause();
        } else {
          throw e;
        }
      }

      // 2. check sign
      String appId = serviceRequest.getAppId();
      SecretKeyService secretKeyService = (SecretKeyService) SpringContext.getContext()
          .getBean("secretKeyService");
      SecretKey secretKey = secretKeyService.findByAppId(appId);

      if (secretKey == null) {
        throw new ServiceException(ResponseCode.REQUEST_ERROR);
      }

      serviceRequest.getParameters().put("parkId", secretKey.getParkId());

      if (!IGNORE_SIGN
          && !Signature.verify(serviceRequest.getParameters(), secretKey.getSecretKey())) {
        throw new ServiceException(ResponseCode.SIGN_FAILED);
      }

      /*
       * if (StringUtils.isEmpty(secretKey.getSecretKey()) ||
       * !Signature.verify(serviceRequest.getParameters(),
       * secretKey.getSecretKey())) { throw new
       * ServiceException(ResponseCode.SIGN_FAILED); }
       */

      // 3. get service bean
      RequestMapping requestMapping = serviceRequest.getRequestMapping();

      AbstractController service = requestMapping.getService();
      if (service == null) {
        throw new ServiceException(ResponseCode.REQUEST_ERROR);
      }

      // version support
      if (!service.versionSupport(serviceRequest.getAppVersion())) {
        throw new ServiceException(ResponseCode.NOT_SUPPORT_VERSION);
      }

      // 4. execute process
      Method method = requestMapping.getMethod();

      // 5. check before process
      // 1) check parameters
      // String token = serviceRequest.getToken();
      RequiredParams params = method.getAnnotation(RequiredParams.class);
      if (params != null) {
        String missingInfo = service.validRequiredParam(serviceRequest, params.params());
        if (missingInfo != null) {
          throw new ServiceException(missingInfo, ResponseCode.MISS_REQUIRED);
        }
      }

      // 2) check authorized
      Authorize authorize = method.getAnnotation(Authorize.class);
      if (authorize == null || authorize.login()) {
        // if (!service.isAuthorized(serviceRequest.getToken()))
        // throw new ServiceException(ResponseCode.NOT_AUTHORIZED);
      }

      ServiceResponse response = null;
      try {
        response = (ServiceResponse) method.invoke(service, serviceRequest);
      } catch (Exception e) {
        Throwable t = e.getCause();
        if (t instanceof ServiceException) {
          if (t.getCause() == null) {
            throw (ServiceException) e.getCause();
          } else {
            throw e;
          }
        } else {
          throw e;
        }
      }

      logger.info("请求ip:" + serviceRequest.getDeviceIP());
      logger.info("请求方法:" + service.getClass().getName() + "." + method.getName());
      logger.info("结果:" + response.toJson());
      logger.info("耗时：" + (System.currentTimeMillis() - start) + "ms");
      logger.info("=====执行结束=====");
      // if (!IS_PRODUCTION) {
      // logger.debug("This service takes " + (System.currentTimeMillis() -
      // start) + " ms.");
      // }
      return response;
    } catch (ServiceException se) {
      logger.info("Service exception: " + se.getMessage());
      logger.info(uri + parameters);
      return new ServiceResponse(se.getResponseCode(), se);
    } catch (Exception e) {
      logger.error("Unknown exception: ", e);
      return new ServiceResponse(ResponseCode.SERVER_ERROR);
    }
  }

  private void writeResponse(Channel channel, ServiceResponse response) throws IOException {
    String json = response.toJson();

//    ByteBuf buf = copiedBuffer(json, CharsetUtil.UTF_8);
    ByteBuf buf = copiedBuffer(json, CharsetUtil.UTF_8);

    boolean close = request.headers().contains(CONNECTION, CLOSE, true)
        || request.getProtocolVersion().equals(HttpVersion.HTTP_1_0)
            && !request.headers().contains(CONNECTION, KEEP_ALIVE, true);

    FullHttpResponse httpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
        HttpResponseStatus.OK, buf);
    httpResponse.headers().set(CONTENT_TYPE, "application/json; charset=UTF-8");

    if (!close) {
      // There's no need to add 'Content-Length' header
      // if this is the last response.
      httpResponse.headers().set(CONTENT_LENGTH, buf.readableBytes());
    }

    /*
     * Set<Cookie> cookies; String value = request.headers().get(COOKIE); if
     * (value == null) { cookies = Collections.emptySet(); } else { cookies =
     * ServerCookieDecoder.STRICT.decode(value); }
     */

    if (!cookies.isEmpty()) {
      for (Cookie cookie : cookies) {
        httpResponse.headers().add(SET_COOKIE, ServerCookieEncoder.STRICT.encode(cookie));
      }
    }

    ChannelFuture future = channel.writeAndFlush(httpResponse);

    if (close) {
      future.addListener(ChannelFutureListener.CLOSE);
    }
  }

  private void readHttpDataChunkByChunk(Map<String, Object> parameters) throws Exception {
    try {
      while (decoder.hasNext()) {
        InterfaceHttpData data = decoder.next();

        if (data != null) {
          try {
            switch (data.getHttpDataType()) {
            case Attribute:
              Attribute attribute = (Attribute) data;
              parameters.put(attribute.getName(), attribute.getValue());
              break;
            case FileUpload:
              FileUpload fileUpload = (FileUpload) data;
              if (fileUpload.isCompleted()) {
                long maxLength = maxFileLength > 0 ? maxFileLength : DEFAULT_MAX_FILE_LENGTH;
                if (fileUpload.length() > maxLength) {
                  logger.error("File length is too long: " + fileUpload.length());
                  throw new Exception("File length is too long: " + fileUpload.length());
                }

                StringBuilder file = new StringBuilder();
                file.append(DiskFileUpload.baseDirectory);
                file.append("/");
                file.append(RandomStringUtils.randomAlphanumeric(16));
                File tmp = new File(file.toString());
                fileUpload.renameTo(tmp);
                parameters.put(fileUpload.getName(), file.toString());
              } else {
                logger.error("File is should be continued, but it is not.");
                throw new Exception("File is should be continued, but it is not.");
              }
              break;
            default:
              break;
            }
          } finally {
            data.release();
          }
        }
      }
    } catch (HttpPostRequestDecoder.EndOfDataDecoderException e) {
      logger.debug("\r\n\r\nEND OF CONTENT CHUNK BY CHUNK\r\n\r\n");
    }
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    // super.exceptionCaught(ctx, cause);
  }
}

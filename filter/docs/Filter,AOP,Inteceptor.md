## 🔥 **Difference between Filters, Interceptors, and AOP and how to utilize them with real-world examples**.
All three of them play a role in intercepting requests and responses in Spring web applications, but they have different purposes and behavior.
### **Frequently used scenarios in practice**.
> “When a user logs into a web service, we need to use a JWT token to authenticate, log, and measure performance.”

---]

# ✅ **1. Filter: Intercept requests and responses first**.
### **📍 Real-world use case 1: JWT token authentication***
> “I need to **verify the validity of the JWT token** on every API request.”

#### **🚀 Example code: JWT Authentication filter
```java
@Component
public class JwtAuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String token = req.getHeader(“Authorization”);
        if (token == null || !isValidToken(token)) {
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED, “Invalid Token”);
            return;
        }

        // pass the request to the next step (interceptor or controller) chain.doFilter(req, res); }
    }

    } private boolean isValidToken(String token) {
        return token.equals(“VALID_TOKEN”); // actually adds JWT validation logic
    }
}
````
---]
### **Features of the filter
Runs first (at the servlet container level)  
Can watch all requests (`/api/*`, including static resources)  
✅ **Frequently used for security functions (JWT validation, CORS configuration, etc.)  
❌ **Difficult to modify responses after the controller is executed**]

---]

# ✅ **2. Interceptor: Intercepts before and after the controller executes** ❌ **Difficult to modify response after controller execution** --- # ✅ **3.
### **📍 Real Use Case 2: User Role Check**]
> “We need to check if the user is an Admin, and if not, we need to block access.”

#### **🚀 Example code: checking for admin with an interceptor
```java
@Component
public class AdminRoleInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String role = request.getHeader(“User-Role”);
        if (!“ADMIN”.equals(role)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, “Admin access required”);
            return false; // Controller not executed
        }
        } return true; // Controller launched
    }
}
```
#### **🚀 Register the interceptor in WebConfig** **🚀 Register the interceptor in WebConfig
```java
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private AdminRoleInterceptor adminRoleInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(adminRoleInterceptor)
                .addPathPatterns(“/admin/**”); // Intercept only the path “/admin”
    }
}
```
---]
### **📌 Features of the interceptor** **!
✅ **Can intercept requests just before controller execution**.  
✅ **Modify requests (`preHandle`)** ✅ **Modify responses (`postHandle`)  
Can modify the response (`postHandle`, `afterCompletion`)** ✅ **Can modify the response (`postHandle`, `afterCompletion`)  
✅ **Often used for security, authentication (login), logging, and more**.  
❌ **It is junior to filters, so it is difficult to process all requests.**

---]

# ✅ **3. AOP (Aspect-Oriented Programming): Intercepting before and after the execution of a specific method** ❌ **It is used for security, authentication, logging, etc.
### **📍 Real-world use case 3: Logging API response time***
> “I want to measure and log the execution time of all API methods.”

#### **🚀 Example code: Execution time measurement using AOP** **## **4CD↩ Real world use case 4: Logging API response times
```java
@Aspect
@Component
public class TimerAop {

    @Pointcut(“within(com.example.controller..*)”) // Applies to the entire controller
    public void timerPointCut(){}

    @Around(“timerPointCut()”)
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Object result = joinPoint.proceed(); // execute the original method

        stopWatch.stop();
        System.out.println(“Execution Time: ‘ + stopWatch.getTotalTimeMillis() + ’ ms”);
        return result;
    }
}
````
---.
### **📌 Features of AOP** **Features of AOP
✅ **Can intercept before and after the execution of a specific method**.  
Can add common functionality (logging, transactions, security, etc.) separated from business logic  
✅ **Often used for performance monitoring, transaction management, logging, etc.  
❌ **Doesn't directly handle HTTP requests (just watches method execution)** ❌ **Doesn't intercept HTTP requests.

---]

# 🎯 **📌 Filter vs Interceptor vs AOP Comparison**
| Features | Filter (`Filter`) | Interceptor (`Interceptor`) | AOP (`@Aspect`) |
|------|---------------|------------------|---------------|
| Applies to | **All HTTP requests** (including static resources) | **Only requests from Spring controllers** | **Method execution itself** |
| When to execute | **Before servlet execution** (first to execute) | **Before and after controller execution** | **Before and after specific method execution** |
| Purpose of use | Security (JWT, CORS, authentication) | Login checks, authorization checks | Logging, performance measurement, transactions
Controller Access | ❌ Inaccessible | ✅ Accessible | ✅ Accessible | ✅ Accessible | ✅ Accessible
| Performance impact | High (intercepts all requests) | Medium | Low (watches only certain methods) |

---]

# ✅ **🚀 When to use it in practice?
| Usage scenarios | Filters | Interceptors | AOP |
|-------------|------|--------|----|
| Apply to all requests (ex. JWT authentication, CORS) | ✅ | ❌ | ❌ |
| Check login user permissions | ❌ | ✅ | ❌ | ❌ |
| Measure API execution time | ❌ | ❌ | ✅ | ❌ | ✅ |
| Log before/after execution of specific methods | ❌ | ❌ | ✅ |
| Transform request data (e.g., change phone number format) | ✅ | ✅ | ✅ | ✅ |

---]

# 🚀 **📌 This is how we use it in practice!** 1.
1. **JWT Authentication** → Use `Filter`  
   → “JWT token verification is required for all requests, so the filter is appropriate.”
2. **Check admin permissions** → Use `Interceptor`  
   → “Interceptor is suitable because it should only be applied on the admin page (`/admin/**` path).”
3. **Enable API runtime logging** → Use `AOP`  
   → “AOP is appropriate because we need to separate it from the business logic and measure execution time as a common function.”

---]

## 🎯 **🔥 Conclusion: Practical uses of filters, interceptors, and AOP** 1.
- Filters → Security & Authentication (JWT, CORS, IP blocking)
- Interceptors** → Authorization checks, request/response processing
- AOP** → Logging, performance analysis, transaction processing

✔ **In practice, it is applied in the order of `Filter → Interceptor → AOP`.  
✔ **They have different roles and should be used in appropriate situations.

Now you should be clear about the differences and how to apply them in practice! 🚀 !  
If you have any further questions, please ask! 😊🔥

Translated with DeepL.com (free version)
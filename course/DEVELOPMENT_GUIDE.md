# 🚀 Development Guide - Seat Booking Backend

Two ways to run the application:

---

## 🔥 **Option 1: Fast Development (Recommended)**

Best for daily coding - super fast iterations!

### **Setup (one-time):**

1. **Start only the database:**
```bash
cd course
docker-compose -f docker-compose-db-only.yaml up -d
```

2. **Run the app from IntelliJ:**
   - Open the project in IntelliJ
   - Click the **green play button** to run `CourseApplication`
   - App connects to MySQL at `localhost:3307`

### **Daily Workflow:**

1. ✏️ **Make code changes**
2. ▶️ **Click Run** in IntelliJ (or Shift+F10)
3. 🧪 **Test** in Postman at `http://localhost:8080`

**That's it!** No Docker rebuild needed! 🎉

### **Stop Everything:**
```bash
# Stop the database
cd course
docker-compose -f docker-compose-db-only.yaml down

# Stop the app: Click red square in IntelliJ
```

---

## 🐋 **Option 2: Full Docker (Production-like)**

Best for testing the complete containerized setup.

### **Build & Run:**

1. **Build the JAR:**
```bash
cd course
.\mvnw.cmd clean package -DskipTests
```

2. **Start everything in Docker:**
```bash
docker-compose up --build -d
```

3. **Test** in Postman at `http://localhost:8080`

### **After Code Changes:**

1. ✏️ **Make code changes**
2. 🔨 **Rebuild JAR:**
   ```bash
   cd course
   .\mvnw.cmd clean package -DskipTests
   ```
3. 🐋 **Rebuild Docker:**
   ```bash
   docker-compose down
   docker-compose up --build -d
   ```
4. 🧪 **Test** in Postman

### **Stop Everything:**
```bash
cd course
docker-compose down
```

---

## 🎯 **Which Should I Use?**

| Scenario | Recommended |
|----------|-------------|
| Daily coding & debugging | **Option 1** (IntelliJ + DB only) |
| Quick iterations | **Option 1** |
| Testing before deployment | **Option 2** (Full Docker) |
| Demo to team | **Option 2** |
| Production deployment | **Option 2** |

---

## 🔍 **Troubleshooting**

### **App can't connect to database:**

**If using IntelliJ (Option 1):**
- Check if MySQL Docker is running: `docker ps`
- Should see `seat-booking-db` container
- If not: `cd course && docker-compose -f docker-compose-db-only.yaml up -d`

**If using Full Docker (Option 2):**
- Check logs: `docker logs seat-booking-backend-app`
- Make sure you built the JAR first: `.\mvnw.cmd clean package -DskipTests`

### **Port already in use:**
- Something is using port 8080 or 3307
- Stop other apps or change ports in `application.yml` / `docker-compose.yaml`

### **Changes not reflected:**

**IntelliJ:** 
- Make sure you clicked "Run" to restart the app
- Try "Rebuild Project" from Build menu

**Docker:**
- You need to rebuild: `mvnw.cmd clean package` → `docker-compose up --build`

---

## 📌 **Database Access**

### **Connect to MySQL (for debugging):**

**Option 1 - IntelliJ Database Tool:**
- Host: `localhost`
- Port: `3307`
- Database: `seat_booking_db`
- User: `user`
- Password: `user1234`

**Option 2 - Command Line:**
```bash
docker exec -it seat-booking-db mysql -u user -puser1234 seat_booking_db
```

---

## 🎓 **Summary**

✅ **For Development:** Use IntelliJ + Docker DB (Option 1)  
✅ **For Production Testing:** Use Full Docker (Option 2)  
✅ **API Documentation:** See `POSTMAN_TESTING_GUIDE.md`

**Happy Coding! 🚀**


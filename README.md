## 📦 Project Overview

A collection of advanced Java OOP projects for inventory, shopping, and insurance management. Each project is fully containerized with Docker for easy deployment and testing.

**Projects included:**
- **StockManagement**: Manage warehouse inventory, suppliers, and stock items interactively.
- **OnlineShoppingSystem**: Simulate an e-commerce experience with cart, checkout, and multiple product categories.
- **InsuranceSystem**: Manage vehicle insurance policies, file claims, and generate reports.

---

## 🛠️ Quick Start: Clone, Build, and Run Each Project

### 1. Clone the Repository
```sh
git clone https://github.com/BonaneN/BonaneNIYIGENA_OOP.git
cd BonaneNIYIGENA_OOP
```

### 2. Build a Project's Docker Image
Replace `<ProjectDir>` and `<image-name>` as needed:
```sh
cd <ProjectDir>
docker build -t <image-name> .
```
Examples:
- StockManagement: `cd stockManagementSystem && docker build -t stock-management-system .`
- OnlineShoppingSystem: `cd onlineShoppingSystem && docker build -t online-shopping-system .`
- InsuranceSystem: `cd insuranceSystem && docker build -t insurance-system .`

### 3. Run the Project's Docker Container
```sh
docker run --rm -it <image-name>
```
Examples:
- `docker run --rm -it stock-management-system`
- `docker run --rm -it online-shopping-system`
- `docker run --rm -it insurance-system`

---

## How to Pull and Run My Projects from Docker Hub in your terminal 

You can easily run any of my Java projects using Docker. Just follow these steps:

### 1. Pull the Image
Choose the project you want and run the corresponding command:

- **Stock Management**
  ```sh
  docker pull bonanen/stock-management-system
  ```
- **Online Shopping System**
  ```sh
  docker pull bonanen/online-shopping-system
  ```
- **Insurance System**
  ```sh
  docker pull bonanen/insurance-system
  ```

### 2. Run the Project
After pulling, run the project with:

- **Stock Management**
  ```sh
  docker run --rm -it bonanen/stock-management-system
  ```
- **Online Shopping System**
  ```sh
  docker run --rm -it bonanen/online-shopping-system
  ```
- **Insurance System**
  ```sh
  docker run --rm -it bonanen/insurance-system
  ```

---

## Direct Docker Hub Links
To check project docker repositories use this link
https://hub.docker.com/repositories/bonanen

---

## 👇Author

**Bonane NIYIGENA**  
**Student at AUCA with ID: 26900**

---

**Follow the above steps then you will be able to run all projects just in your terminal.**

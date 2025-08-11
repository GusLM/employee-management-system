\# Changelog



All notable changes to this project will be documented in this file.  

The format is based on \[Keep a Changelog](https://keepachangelog.com/en/1.0.0/).



---



\## \[Unreleased]



---



\## 2025-08-08

\### Added

\- Created `README.md` file (`06eeb57`)



---



\## 2025-08-07

\### Changed

\- Instantiated controller methods in `ConsoleUI` and finalized the main loop execution logic while the program is running (`5e0135b`)



---



\## 2025-08-06

\### Changed

\- Refactored: adjusted loop logic in `removeEmployee`, instantiated `Service` in `Controller`, and cleaned up unused modules/files (`4f5f578`)



---



\## 2025-08-05

\### Added

\- Added method to remove employee based on CPF (`0420f98`)



---



\## 2025-08-03

\### Added

\- Added method to edit employee data (`f971af6`)



---



\## 2025-08-02

\### Added

\- Added method to display employee data based on name or CPF (`1e5a388`)



---



\## 2025-08-01

\### Added

\- Added method to generate monthly reports based on employee's CPF (`02ee68b`)



---



\## 2025-07-31

\### Added

\- Added methods to search employees by name or CPF and display net salary (`bf2b978`)



---



\## 2025-07-30

\### Added

\- Added method using lambda expression to filter employees by name or CPF (`e5df2bc`)



---



\## 2025-07-27

\### Changed

\- Improved encapsulation in `EmployeeRegistryService` by returning an unmodifiable employee list (`f14f881`)

\- Reorganized package structure and started implementing business logic in the service layer (`3e9712e`)



---



\## 2025-07-26

\### Added

\- Added enums for employee role and performance level, and split responsibilities in the service (`0a8d174`)



---



\## 2025-07-25

\### Added

\- Initial structure of `ConsoleUI` class and utility for validating integer inputs (`6e8354b`)



---



\## 2025-07-24

\### Added

\- Implemented IRRF (Brazilian income tax) calculation service for 2025 (`313482f`)

\- Changed salary calculations from `double` to `BigDecimal` and added INSS (social security) calculator (`912a5c6`)



---



\## 2025-07-23

\### Added

\- Initial project structure and creation of base `Employee` class (`d36c35d`)


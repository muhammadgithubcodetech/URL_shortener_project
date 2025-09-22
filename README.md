# -URL-Shortener-Redirection-Analysis-Tool
A custom-built URL shortening service implemented in Java (Spring Boot), created to explore how shortened URLs work and how they can be exploited in phishing, redirection attacks, and evasion techniques.

This project was developed as part of a cybersecurity learning initiative to:

- Understand the internal workings of URL shortening and redirection mechanisms.
- Investigate how shortened links are used in phishing and malware distribution.
- Build a custom URL shortener with a focus on control, observability, and potential for abuse analysis.
- Lay the foundation for creating security tools that detect or simulate malicious link usage.

## Features

- Generates shortened URLs using a unique hash
- Redirects users to the original destination
- Separation of concerns using service, controller, and main application layers
- Potential extension into URL logging, expiration, and analytics

## Security Perspective

This tool provides insight into the risks associated with shortened URLs, including:

- Obfuscation of malicious or suspicious destinations
- Use in phishing and social engineering campaigns
- Redirection-based evasion of content filters or scanners

The project also serves as a learning platform for secure design principles when building redirection-based systems.

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven

### How to Run

```bash
cd projectdsa
mvn spring-boot:run

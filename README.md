# Portfolio Rebalancing

## Introduction to Data, Agrona and Problem

Build a high-performance rebalancing engine that:

- Reads existing portfolio holdings (`portfolio.csv`)
- Reads a target model composition (`target.csv`)
- Computes trades required to rebalance into the target
- Outputs a trade plan with minimum round-off error and performance constraints (save in `trades.csv`)

All core logic should use **Agrona** data structures (e.g., `MutableDirectBuffer`, `ExpandableArrayBuffer`, `IntArrayList`, etc.) to optimize memory & CPU.

---

## Agrona Components (Must Be Used)

- **Buffers**  
  Thread-safe direct and atomic buffers for working with on and off-heap memory with memory ordering semantics.

- **Lists**  
  Array-backed lists of `int`/`long` primitives to avoid boxing.

- **Maps (primitive → object)**  
  Open addressing and linear probing with `int`/`long` primitive keys to object reference values.

- **Maps (primitive → primitive)**  
  Open addressing and linear probing with `int`/`long` primitive keys to `int`/`long` values.

- **Sets**  
  Open addressing and linear probing for `int`/`long` primitives and object references.

- **Cache**  
  Set associative cache with `int`/`long` primitive keys to object reference values.

- **Clocks**  
  Clock implementations to abstract system clocks, allow caching, and enable testing.

- **Queues**  
  Lock-less implementations for low-latency applications.

- **Ring/Broadcast Buffers**  
  Implemented off-heap for IPC communication.

- **Simple Agent Framework**  
  For concurrent services.

- **Scalable Timer Wheel**  
  For scheduling timers at a given deadline with O(1) register and cancel time.

- **Code Generation**  
  From annotated implementations specialized for primitive types.

- **Off-heap Counters**  
  For application telemetry, position tracking, and coordination.

- **InputStream / OutputStream Implementations**  
  That can wrap direct buffers.

- **DistinctErrorLog**  
  A log of distinct errors to avoid filling disks with repetitive logging.

- **IdGenerator**  
  Concurrent and distributed unique ID generator employing a lock-less implementation of the Twitter Snowflake algorithm.

**Agrona Repository:**  
https://github.com/aeron-io/agrona

---

## Solution Requirements

The solution must demonstrate:

- Low GC pressure
- High throughput
- Predictable latency
- O(N) complexity

---

# Tests

Implement comprehensive automated tests validating:

- Functional correctness
- Numerical precision
- Edge cases
- Performance characteristics
- Memory/allocation behavior (explainable)

Tests must be written using **JUnit 5**.  
No manual verification is accepted.

---

## Functional Test Scenario

### Description

Portfolio partially overlaps with model.

### Given

#### Model:
- AAPL 50%
- MSFT 50%

#### Portfolio:
- AAPL 100 shares @ 100
- GOOGL 100 shares @ 50

---

### Expected Behavior

- AAPL adjusted to 50% of total
- MSFT bought
- GOOGL fully sold

---

### Assertions

- All model tickers appear in output
- All non-model tickers are fully sold
- Trade values are correct within tolerance
- Sum of trade values ≈ 0

---

## Numerical Precision

Ensure proper floating-point handling and minimal rounding error.

---

## Edge Case Behavior: Zero Price Handling

### Scenario

Position has `price = 0`

### Expected

- Skip trade
- Log warning (or collect error state)
- No division by zero

---

# Performance Tests and Metrics

Implement another `.java` test file to test implementation under the following conditions:

- 1 model with 200 tickers
- 1000 portfolios (each containing a minimum of 50 tickers)

---

## Required Metrics

The result should contain the following metrics:

- Total processing time
- Positions processed
- Throughput (positions/sec)
- Average per portfolio latency

---

## Example: Total Processing Time Measurement

```java
long start = System.nanoTime();

for (int i = 0; i < 1000; i++)
{
    engine.rebalance(model, portfolios[i], trades[i]);
}

long end = System.nanoTime();

Endpoint:

POST localhost:8080/validation/trade

Sample json:
```javascript
{
  "trades": [
    {
      "customer": "PLUTO1",
      "ccyPair": "EURUSD",
      "type": "Spot",
      "direction": "BUY",
      "tradeDate": "2016-08-11",
      "amount1": 1000000.00,
      "amount2": 1120000.00,
      "rate": 1.12,
      "valueDate": "2016-08-15",
      "legalEntity": "CS Zurich",
      "trader": "Johann Baumfiddler"
    }
  ]
}

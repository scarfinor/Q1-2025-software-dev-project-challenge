import React from "react";
import "../css/Dashboard.css";

const Dashboard = () => {
  const financeData = {
    income: 5000,
    expenses: 2200,
    netWorth: 2800,
  };

  return (
    <div className="dashboard-page">
      <div className="finance-dashboard-container">
        <h1 className="dashboard-title">Personal Finance Dashboard</h1>
        <table className="finance-table">
          <thead>
            <tr>
              <th>Category</th>
              <th>Amount</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>Income</td>
              <td>${financeData.income}</td>
            </tr>
            <tr>
              <td>Expenses</td>
              <td>-${financeData.expenses}</td>
            </tr>
            <tr>
              <td>Net Worth</td>
              <td>${financeData.netWorth}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default Dashboard;

class EquationSolver:
    def __init__(self, coefficients, target, limits):
        self.coeffs = coefficients
        self.target = target
        self.limits = limits
        self.variables = list(coefficients.keys())
        self.solutions = []

    def solve(self):
        # Logic: Check for infinite solutions (no limits provided)
        for var in self.variables:
            if var not in self.limits:
                return "Infinite answers detected. Please apply market limits."
        
        self._backtrack(0, 0, {})
        
        if not self.solutions:
            return "No whole number solutions found within these limits."
        
        return self.solutions

    def _backtrack(self, var_idx, current_sum, current_sol):
        # If we have assigned all variables
        if var_idx == len(self.variables):
            if current_sum == self.target:
                self.solutions.append(dict(current_sol))
            return

        var = self.variables[var_idx]
        low, high = self.limits[var]

        # Iterate through all possible whole numbers for this variable
        for val in range(low, high + 1):
            current_sol[var] = val
            new_sum = current_sum + (self.coeffs[var] * val)
            self._backtrack(var_idx + 1, new_sum, current_sol)
            
        # Optimization: Limit return to first 1000 solutions to prevent browser crash
        if len(self.solutions) > 1000:
            return
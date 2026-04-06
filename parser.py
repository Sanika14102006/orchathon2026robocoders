import re

class AlgebraicParser:
    @staticmethod
    def parse_equation(eq_str):
        # Remove spaces
        eq_str = eq_str.replace(" ", "")
        
        if "=" not in eq_str:
            raise ValueError("Invalid Equation: Missing '=' sign.")

        left_side, right_side = eq_str.split("=")
        
        try:
            target_value = int(right_side)
        except ValueError:
            raise ValueError("Right side of equation must be a whole number.")

        # Regex to find components like '150a', '+100b', '-50', 'x'
        # Pattern explains: Optional sign, optional digits, optional variable
        pattern = r'([+-]?\d*)([a-zA-Z]?)'
        matches = re.findall(pattern, left_side)
        
        coefficients = {}
        constant_offset = 0

        for num_str, var in matches:
            if not num_str and not var:
                continue
            
            # Handle cases like '+x' or '-x' or just 'x'
            if num_str == "+" or num_str == "" and var:
                val = 1
            elif num_str == "-" and var:
                val = -1
            elif num_str:
                val = int(num_str)
            else:
                val = 0

            if var:
                coefficients[var] = coefficients.get(var, 0) + val
            else:
                constant_offset += val

        return coefficients, target_value - constant_offset
from flask import Flask, render_template, request, jsonify
from solver import AlgebraSolver

app = Flask(_name_)
solver = AlgebraSolver()

@app.route('/')
def index():
    return render_template('dashboard.html')

@app.route('/solve', methods=['POST'])
def solve():
    data = request.get_json()
    eq = data.get('equation')
    limits = data.get('limits')
    try:
        solver.parse_equation(eq)
        solutions = solver.solve_integer_solutions(limits)
        return jsonify(solutions)
    except Exception as e:
        return jsonify(f"Error: {str(e)}")

if _name_ == "_main_":
    app.run(debug=True)
#!/usr/bin/env python3
"""
Convert FTC Curriculum markdown to HTML with proper hierarchical structure
"""

import re

def markdown_to_html(md_text):
    """Convert markdown to HTML preserving nested structure from indentation."""
    lines = md_text.split('\n')
    html_lines = []
    list_stack = []  # Track (indent_level, tag_type)
    
    def close_lists_to_level(target_indent):
        """Close list tags down to target indent level."""
        nonlocal list_stack
        while list_stack and list_stack[-1] >= target_indent:
            html_lines.append('</li>')
            html_lines.append('</ul>')
            list_stack.pop()
    
    def format_inline(text):
        """Apply inline formatting to text."""
        # Convert links first (to avoid breaking them with other replacements)
        text = re.sub(r'\[([^\]]+)\]\(([^)]+)\)', r'<a href="\2">\1</a>', text)
        # Convert bold (double asterisks)
        text = re.sub(r'\*\*(.*?)\*\*', r'<strong>\1</strong>', text)
        # Convert italic (single asterisks, but not part of bold)
        text = re.sub(r'(?<!\*)\*(?!\*)([^*]+?)(?<!\*)\*(?!\*)', r'<em>\1</em>', text)
        # Convert inline code
        text = re.sub(r'`([^`]+)`', r'<code>\1</code>', text)
        return text
    
    i = 0
    while i < len(lines):
        line = lines[i]
        indent = len(line) - len(line.lstrip(' '))
        stripped = line.strip()
        
        # Empty lines
        if not stripped:
            i += 1
            continue
        
        # Headers
        if stripped.startswith('#### '):
            close_lists_to_level(-1)
            html_lines.append(f'<h4>{format_inline(stripped[5:])}</h4>')
        elif stripped.startswith('### '):
            close_lists_to_level(-1)
            html_lines.append(f'<h3>{format_inline(stripped[4:])}</h3>')
        elif stripped.startswith('## '):
            close_lists_to_level(-1)
            html_lines.append(f'<h2>{format_inline(stripped[3:])}</h2>')
        elif stripped.startswith('# '):
            close_lists_to_level(-1)
            html_lines.append(f'<h1>{format_inline(stripped[2:])}</h1>')
        
        # Bullet points with nested structure
        elif stripped.startswith('* '):
            # Close lists if we've decreased indentation
            close_lists_to_level(indent)
            
            # Check if we need to close the previous li
            if list_stack and list_stack[-1] == indent:
                html_lines.append('</li>')
            
            # Open new list if needed (deeper nesting)
            if not list_stack or list_stack[-1] < indent:
                html_lines.append('<ul>')
                list_stack.append(indent)
            
            # Add list item (leave it open for potential nested content)
            content = format_inline(stripped[2:])
            html_lines.append(f'<li>{content}')
        
        # Horizontal rule
        elif stripped == '---':
            close_lists_to_level(-1)
            html_lines.append('<hr>')
        
        # Regular paragraph
        else:
            # Check if this is a continuation of a list item (has indent)
            if list_stack and indent > 0:
                # This is content within a list item
                html_lines.append(f'<div class="list-content">{format_inline(stripped)}</div>')
            else:
                close_lists_to_level(-1)
                # Check if it's a "Notes" or special paragraph
                if stripped.startswith('Notes '):
                    html_lines.append(f'<div class="note">{format_inline(stripped)}</div>')
                else:
                    html_lines.append(f'<p>{format_inline(stripped)}</p>')
        
        i += 1
    
    # Close any remaining open lists
    close_lists_to_level(-1)
    
    return '\n'.join(html_lines)

def create_html(md_file, output_file):
    """Create HTML from markdown file"""
    
    with open(md_file, 'r', encoding='utf-8') as f:
        md_content = f.read()
    
    # Convert markdown to HTML
    content_html = markdown_to_html(md_content)
    
    # Create full HTML document
    html = f"""<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FTC Robotics Curriculum Outline</title>
    <style>
        :root {{
            --primary-color: #2c3e50;
            --secondary-color: #3498db;
            --accent-color: #e74c3c;
            --bg-color: #f8f9fa;
            --text-color: #333333;
            --code-bg: #f4f4f4;
            --border-color: #dddddd;
            --highlight-bg: #fff9e6;
            --session-bg: #f0f7ff;
            --task-bg: #ffffff;
        }}

        * {{
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }}

        body {{
            font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, 'Helvetica Neue', Arial, sans-serif;
            line-height: 1.6;
            color: var(--text-color);
            background: var(--bg-color);
            padding: 2rem;
        }}

        .container {{
            max-width: 1200px;
            margin: 0 auto;
            background: white;
            padding: 3rem;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
            border-radius: 8px;
        }}

        h1 {{
            font-size: 2.5rem;
            color: var(--primary-color);
            margin-bottom: 1.5rem;
            border-bottom: 3px solid var(--secondary-color);
            padding-bottom: 0.5rem;
        }}

        h2 {{
            font-size: 2rem;
            color: var(--primary-color);
            margin-top: 3rem;
            margin-bottom: 1.5rem;
            border-bottom: 2px solid var(--secondary-color);
            padding-bottom: 0.5rem;
            padding-top: 1rem;
        }}

        h3 {{
            font-size: 1.5rem;
            color: var(--secondary-color);
            margin-top: 2rem;
            margin-bottom: 1rem;
            font-weight: 600;
        }}

        h4 {{
            font-size: 1.25rem;
            color: #2980b9;
            margin-top: 1.5rem;
            margin-bottom: 1rem;
            font-weight: 600;
        }}

        p {{
            margin: 1rem 0;
            line-height: 1.8;
        }}

        a {{
            color: var(--secondary-color);
            text-decoration: none;
            transition: color 0.2s;
            font-weight: 500;
        }}

        a:hover {{
            color: var(--accent-color);
            text-decoration: underline;
        }}

        code {{
            font-family: 'Monaco', 'Menlo', 'Ubuntu Mono', 'Consolas', monospace;
            background: var(--code-bg);
            padding: 0.2rem 0.4rem;
            border-radius: 3px;
            font-size: 0.9em;
            color: var(--accent-color);
        }}

        ul {{
            list-style: none;
            margin: 0;
            padding: 0;
        }}

        /* Top-level list items (Sessions) */
        ul > li {{
            margin: 1.5rem 0;
            padding: 1rem;
            background: var(--session-bg);
            border-left: 4px solid var(--secondary-color);
            border-radius: 4px;
        }}

        /* Second-level list items (Tasks) */
        ul > li > ul {{
            margin-top: 1rem;
        }}

        ul > li > ul > li {{
            margin: 1rem 0 1rem 1.5rem;
            padding: 0.75rem;
            background: var(--task-bg);
            border-left: 3px solid #95a5a6;
            border-radius: 4px;
        }}

        /* Third-level list items (Task details like Goal, Time, Hardware, Resource) */
        ul > li > ul > li > ul {{
            margin-top: 0.5rem;
        }}

        ul > li > ul > li > ul > li {{
            margin: 0.25rem 0 0.25rem 1.5rem;
            padding: 0.25rem 0;
            background: transparent;
            border: none;
            list-style: disc;
            list-style-position: outside;
        }}

        strong {{
            color: var(--primary-color);
            font-weight: 600;
        }}

        em {{
            color: #666;
            font-style: italic;
        }}

        hr {{
            border: none;
            border-top: 2px solid var(--border-color);
            margin: 3rem 0;
        }}

        .note {{
            background: var(--highlight-bg);
            border-left: 4px solid #f39c12;
            padding: 1.25rem;
            margin: 1.5rem 0;
            border-radius: 4px;
        }}

        .list-content {{
            margin: 0.5rem 0;
            padding-left: 1rem;
        }}

        /* Back to top button */
        .back-to-top {{
            position: fixed;
            bottom: 2rem;
            right: 2rem;
            background: var(--secondary-color);
            color: white;
            width: 50px;
            height: 50px;
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            text-decoration: none;
            font-size: 1.5rem;
            box-shadow: 0 4px 8px rgba(0,0,0,0.2);
            opacity: 0;
            transition: opacity 0.3s, background 0.3s;
            z-index: 1000;
        }}

        .back-to-top.visible {{
            opacity: 1;
        }}

        .back-to-top:hover {{
            background: var(--accent-color);
            text-decoration: none;
        }}

        /* Responsive design */
        @media (max-width: 768px) {{
            body {{
                padding: 1rem;
            }}

            .container {{
                padding: 1.5rem;
            }}

            h1 {{
                font-size: 2rem;
            }}

            h2 {{
                font-size: 1.5rem;
            }}

            h3 {{
                font-size: 1.25rem;
            }}

            h4 {{
                font-size: 1.1rem;
            }}

            ul > li > ul > li {{
                margin-left: 0.5rem;
            }}

            ul > li > ul > li > ul > li {{
                margin-left: 0.5rem;
            }}
        }}

        @media print {{
            body {{
                padding: 0;
                background: white;
            }}
            
            .container {{
                box-shadow: none;
                padding: 1rem;
            }}
            
            .back-to-top {{
                display: none;
            }}
            
            ul > li {{
                page-break-inside: avoid;
            }}
            
            h1, h2, h3, h4 {{
                page-break-after: avoid;
            }}
        }}
    </style>
</head>
<body>
    <div class="container">
{content_html}
    </div>

    <a href="#top" class="back-to-top" id="backToTop">↑</a>

    <script>
        // Back to top button
        const backToTop = document.getElementById('backToTop');
        
        window.addEventListener('scroll', () => {{
            if (window.pageYOffset > 300) {{
                backToTop.classList.add('visible');
            }} else {{
                backToTop.classList.remove('visible');
            }}
        }});

        // Smooth scrolling
        backToTop.addEventListener('click', (e) => {{
            e.preventDefault();
            window.scrollTo({{
                top: 0,
                behavior: 'smooth'
            }});
        }});
    </script>
</body>
</html>"""
    
    with open(output_file, 'w', encoding='utf-8') as f:
        f.write(html)
    
    print(f"✓ Successfully created {output_file}")

if __name__ == '__main__':
    create_html('FTC Curriculum - Draft 1.md', 'FTCCurriculum.html')
